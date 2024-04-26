package com.a307.befresh.module.domain.food.service;

import com.a307.befresh.module.domain.Ftype.Ftype;
import com.a307.befresh.module.domain.Ftype.repository.FtypeRepository;
import com.a307.befresh.module.domain.container.Container;
import com.a307.befresh.module.domain.container.repository.ContainerRepository;
import com.a307.befresh.module.domain.food.Food;
import com.a307.befresh.module.domain.food.dto.request.FoodRegisterReq;
import com.a307.befresh.module.domain.food.dto.request.FoodRegisterReqList;
import com.a307.befresh.module.domain.food.dto.response.FoodDetailRes;
import com.a307.befresh.module.domain.food.dto.response.FoodListDetailRes;
import com.a307.befresh.module.domain.food.repository.FoodRepository;
import com.a307.befresh.module.domain.refresh.Refresh;
import com.a307.befresh.module.domain.refresh.repository.RefreshRepository;
import com.a307.befresh.module.domain.refrigerator.Refrigerator;
import com.a307.befresh.module.domain.refrigerator.repository.RefrigeratorRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class FoodServiceImpl implements FoodService {

    private final FoodRepository foodRepository;
    private final RefrigeratorRepository refrigeratorRepository;
    private final RefreshRepository refreshRepository;
    private final FtypeRepository ftypeRepository;
    private final ContainerRepository containerRepository;

    @Override
    public void blocking() throws InterruptedException {
        Thread.sleep(5000);
    }

    private void asyncRegisterFood(Refrigerator refrigerator, FoodRegisterReq foodRegisterReq) {

        System.out.println(
                Thread.currentThread().toString() + " " + Thread.currentThread().isVirtual());
        System.out.println(foodRegisterReq.name());
        Optional<Ftype> ftype = ftypeRepository.findById(foodRegisterReq.ftypeId());

        // TODO: 신선도 로직 구현 후 변경
        Optional<Refresh> refresh = refreshRepository.findById(1L);

        // TODO: 음식 검색 기능을 통해서 유통기한 설정 -> 검색이 안될 경우 missRegistered true 처리

        if (ftype.get().getId() == 1) {

            Container container = Container.createContainer(foodRegisterReq.name(),
                    foodRegisterReq.image(),
                    foodRegisterReq.expirationDate(),
                    refresh.get(), ftype.get(), refrigerator, false,
                    foodRegisterReq.temperature(),
                    foodRegisterReq.humidity(), foodRegisterReq.zCoordinate(),
                    foodRegisterReq.qrId());

            containerRepository.save(container);

            log.debug("asyncRegisterFood method : container {} success", container.getFoodId());
        } else {
            Food food = Food.createFood(foodRegisterReq.name(),
                    foodRegisterReq.image(),
                    foodRegisterReq.expirationDate(),
                    refresh.get(), ftype.get(), refrigerator, false);

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
                    food.getExpirationDate().toLocalDate()).getDays();
            int remindDays = Period.between(LocalDateTime.now().toLocalDate(),
                    food.getExpirationDate().toLocalDate()).getDays();

            Double freshState = 0.0;

            if (remindDays > 0) {
                freshState = (double) remindDays / totalDays * 100;
            }

            foodListDetailResList.add(
                    FoodListDetailRes.builder()
                            .id(food.getFoodId())
                            .name(food.getName())
                            .image(food.getImage())
                            .expirationDate(food.getExpirationDate())
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

    public FoodDetailRes getFoodDetail(long foodId) {
        return containerRepository.findById(foodId)
                .map(this::createFoodDetailFromContainer)
                .orElseGet(() -> createFoodDetailFromFood(foodId));
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
        double freshState = calculateFreshState(); // 추후 계산 로직 추가

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
