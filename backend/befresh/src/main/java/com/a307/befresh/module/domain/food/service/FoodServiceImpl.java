package com.a307.befresh.module.domain.food.service;

import com.a307.befresh.module.domain.Ftype.Ftype;
import com.a307.befresh.module.domain.Ftype.repository.FtypeRepository;
import com.a307.befresh.module.domain.container.Container;
import com.a307.befresh.module.domain.container.repository.ContainerRepository;
import com.a307.befresh.module.domain.elastic.ElasticDocument;
import com.a307.befresh.module.domain.elastic.repository.ElasticRepository;
import com.a307.befresh.module.domain.food.Food;
import com.a307.befresh.module.domain.food.dto.request.FoodRegisterReq;
import com.a307.befresh.module.domain.food.dto.request.FoodRegisterReqList;
import com.a307.befresh.module.domain.food.dto.request.FoodUpdateReq;
import com.a307.befresh.module.domain.food.dto.response.FoodDetailRes;
import com.a307.befresh.module.domain.food.dto.response.FoodFailRes;
import com.a307.befresh.module.domain.food.dto.response.FoodListDetailRes;
import com.a307.befresh.module.domain.food.repository.FoodRepository;
import com.a307.befresh.module.domain.refresh.Refresh;
import com.a307.befresh.module.domain.refresh.repository.RefreshRepository;
import com.a307.befresh.module.domain.refrigerator.Refrigerator;
import com.a307.befresh.module.domain.refrigerator.repository.RefrigeratorRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Log4j2
@Service
@RequiredArgsConstructor
public class FoodServiceImpl implements FoodService {

    private final FoodRepository foodRepository;
    private final RefrigeratorRepository refrigeratorRepository;
    private final RefreshRepository refreshRepository;
    private final FtypeRepository ftypeRepository;
    private final ContainerRepository containerRepository;
    private final ElasticRepository elasticRepository;

    @Override
    public void blocking() throws InterruptedException {
        Thread.sleep(5000);
    }

    private void asyncRegisterFood(Refrigerator refrigerator, FoodRegisterReq foodRegisterReq) {

        System.out.println(
                Thread.currentThread().toString() + " " + Thread.currentThread().isVirtual());
        System.out.println(foodRegisterReq.name());
        Optional<Ftype> ftypeOptional = ftypeRepository.findById(foodRegisterReq.ftypeId());

        if(ftypeOptional.isEmpty()) return;

        Ftype ftype = ftypeOptional.get();

        // TODO: 신선도 로직 구현 후 변경
        Optional<Refresh> refresh = refreshRepository.findById(1L);

        String name = foodRegisterReq.name();
        LocalDate expirationDate = foodRegisterReq.expirationDate();
        boolean missRegistered = ftype.getId() == 2;

        // elastic search를 통한 음식 검색
        if(ftype.getId() != 2) {
            List<ElasticDocument> elasticDocuments = elasticRepository.searchBefreshByName(
                foodRegisterReq.name());

            if (elasticDocuments != null && !elasticDocuments.isEmpty() ) {
                ElasticDocument top = elasticDocuments.getFirst();

                name = top.getName();
                expirationDate = LocalDate.now().plusDays(top.getExpiration_date());
                missRegistered = true;

                log.debug("Elastic Search - name: {}, expiration_date: {}, score: {}",
                    top.getName(), top.getExpiration_date(), top.getScore());

                for (ElasticDocument elasticDocument : elasticDocuments) {
                    if (elasticDocument.getName().replaceAll("\\s+", "")
                        .equals(foodRegisterReq.name().replaceAll("\\s+", ""))) {
                        name = elasticDocument.getName();
                        expirationDate = LocalDate.now()
                            .plusDays(elasticDocument.getExpiration_date());

                        log.debug("Elastic Search - name: {}, expiration_date: {}, score: {}",
                            elasticDocument.getName(), elasticDocument.getExpiration_date(),
                            elasticDocument.getScore());
                        break;
                    }
                }
            }
        }

        if (ftype.getId() == 1) {

            Container container = Container.createContainer(name,
                    foodRegisterReq.image(),
                    expirationDate,
                    refresh.get(), ftype, refrigerator, missRegistered,
                    foodRegisterReq.temperature(),
                    foodRegisterReq.humidity(), foodRegisterReq.zCoordinate(),
                    foodRegisterReq.qrId());

            containerRepository.save(container);

            log.debug("asyncRegisterFood method : container {} success", container.getFoodId());
        } else {
            Food food = Food.createFood(name,
                    foodRegisterReq.image(),
                    expirationDate,
                    refresh.get(), ftype, refrigerator, missRegistered);

            foodRepository.save(food);

            log.debug("asyncRegisterFood method : food {} success", food.getFoodId());
        }
    }

    @Override
    @Async("virtualExecutor")
    public void registerFood(FoodRegisterReqList foodRegisterReqList) {
        Optional<Refrigerator> refrigerator = refrigeratorRepository.findById(
                foodRegisterReqList.refrigeratorId());
        log.debug("registerFood method start : {} ", Thread.currentThread().toString());

        if (refrigerator.isEmpty()) {
            log.error("registerFood method refirgerator: {}이 없습니다.",
                    foodRegisterReqList.refrigeratorId());
            return;
        }

        for (FoodRegisterReq foodRegisterReq : foodRegisterReqList.foodList()) {
            asyncRegisterFood(refrigerator.get(), foodRegisterReq);
        }

        // TODO: 완료되면 알림처리
        log.debug("registerFood method success : {} ", Thread.currentThread().toString());
    }

    public List<FoodListDetailRes> getFoodList(Long refrigeratorId) {

        List<Food> foodList = foodRepository.findByRefrigerator_Id(refrigeratorId);

        log.debug("getFoodList method read : {} ", foodList.size());
        List<FoodListDetailRes> foodListDetailResList = new ArrayList<>();

        for (Food food : foodList) {

            int elapsedTime = Period.between(food.getRegDttm().toLocalDate(),
                    LocalDateTime.now().toLocalDate()).getDays();

            // TODO: 신선도는 나중에 다르게 설정
            int totalDays = Period.between(food.getRegDttm().toLocalDate(),
                    food.getExpirationDate()).getDays();
            int remindDays = Period.between(LocalDate.now(),
                    food.getExpirationDate()).getDays();

            Double freshState = 0.0;

            if (remindDays > 0) {
                freshState = (double) remindDays / totalDays * 100;
            }

            foodListDetailResList.add(
                    FoodListDetailRes.builder()
                            .id(food.getFoodId())
                            .name(food.getName())
                            .image(food.getImage())
                            .regDttm(food.getRegDttm())
                            .elapsedTime(elapsedTime)
                            .freshState(freshState)
                            .refresh(food.getRefresh().getName())
                            .ftype(food.getFtype().getName())
                            .build()
            );
        }

        log.debug("getFoodList method success");

        return foodListDetailResList;
    }

    @Override
    public FoodDetailRes getFoodDetail(long foodId) {
        return containerRepository.findById(foodId)
                .map(this::createFoodDetailFromContainer)
                .orElseGet(() -> createFoodDetailFromFood(foodId));
    }

    @Override
    public List<FoodFailRes> getFoodFailList(long refrigeratorId) {
        return foodRepository.findFailFood(refrigeratorId).stream()
                .map(food -> FoodFailRes.builder()
                        .id(food.getFoodId())
                        .name(food.getName())
                        .image(food.getImage())
                        .regDttm(food.getRegDttm())
                        .build())
                .toList();
    }

    @Override
    @Transactional
    public void removeFood(Long foodId) {
        Food food = foodRepository.findById(foodId).orElseThrow();
        foodRepository.delete(food);
    }

    @Override
    @Transactional
    public void updateFood(FoodUpdateReq foodUpdateReq) {
        Food food = foodRepository.findById(foodUpdateReq.foodId()).orElseThrow();
        food.setName(foodUpdateReq.name());
        food.setExpirationDate(foodUpdateReq.expirationDate());
        foodRepository.save(food);
    }

    private FoodDetailRes createFoodDetailFromContainer(Container container) {
        int elapsedTime = calculateElapsedTime(container.getRegDttm());
        double freshState = calculateFreshState(); // 추후 계산 로직 추가

        return FoodDetailRes.builder()
                .id(container.getFoodId())
                .name(container.getName())
                .image(container.getImage())
                .expirationDate(container.getExpirationDate())
                .regDttm(container.getRegDttm())
                .elapsedTime(elapsedTime)
                .freshState(freshState)
                .refresh(container.getRefresh().getName())
                .ftype(container.getFtype().getName())
                .temperature(container.getTemperature())
                .humidity(container.getHumidity())
                .build();
    }

    private FoodDetailRes createFoodDetailFromFood(long foodId) {
        Food food = foodRepository.findById(foodId).orElseThrow();
        int elapsedTime = calculateElapsedTime(food.getRegDttm());
        double freshState = calculateFreshState(); // TODO : 추후 계산 로직 추가

        return FoodDetailRes.builder()
                .id(food.getFoodId())
                .name(food.getName())
                .image(food.getImage())
                .expirationDate(food.getExpirationDate())
                .regDttm(food.getRegDttm())
                .elapsedTime(elapsedTime)
                .freshState(freshState)
                .refresh(food.getRefresh().getName())
                .ftype(food.getFtype().getName())
                .build();
    }

    private int calculateElapsedTime(LocalDateTime registrationDateTime) {
        return Period.between(registrationDateTime.toLocalDate(), LocalDate.now()).getDays();
    }

    private double calculateFreshState() {
        return 0.0; // 여기에 신선도 계산 로직을 구현
    }
}
