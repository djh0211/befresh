package com.a307.befresh.module.domain.food.service;

import com.a307.befresh.module.domain.Ftype.Ftype;
import com.a307.befresh.module.domain.Ftype.repository.FtypeRepository;
import com.a307.befresh.module.domain.container.Container;
import com.a307.befresh.module.domain.container.repository.ContainerRepository;
import com.a307.befresh.module.domain.food.Food;
import com.a307.befresh.module.domain.food.dto.request.FoodRegisterReq;
import com.a307.befresh.module.domain.food.dto.request.FoodRegisterReqList;
import com.a307.befresh.module.domain.food.repository.FoodRepository;
import com.a307.befresh.module.domain.refresh.Refresh;
import com.a307.befresh.module.domain.refresh.repository.RefreshRepository;
import com.a307.befresh.module.domain.refrigerator.Refrigerator;
import com.a307.befresh.module.domain.refrigerator.repository.RefrigeratorRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
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

    @Override
    public int registerFood(FoodRegisterReqList foodRegisterReqList) {
        Optional<Refrigerator> refrigerator = refrigeratorRepository.findById(
            foodRegisterReqList.refrigeratorId());

        if (refrigerator.isEmpty()) {
            return 0;
        }

        int cnt = 0;
        for (FoodRegisterReq foodRegisterReq : foodRegisterReqList.foodList()) {
            Optional<Ftype> ftype = ftypeRepository.findById(foodRegisterReq.ftypeId());

            // TODO: 신선도 로직 구현 후 변경
            Optional<Refresh> refresh = refreshRepository.findById(1L);

            // TODO: 음식 검색 기능을 통해서 유통기한 설정 -> 검색이 안될 경우 missRegistered true 처리

            if (ftype.get().getId() == 1) {

                Container container = Container.createContainer(foodRegisterReq.name(),
                    foodRegisterReq.expirationDate(),
                    refresh.get(), ftype.get(), refrigerator.get(), false,
                    foodRegisterReq.temperature(),
                    foodRegisterReq.humidity(), foodRegisterReq.zCoordinate(),
                    foodRegisterReq.qrId());

                containerRepository.save(container);
            } else {
                Food food = Food.createFood(foodRegisterReq.name(),
                    foodRegisterReq.expirationDate(),
                    refresh.get(), ftype.get(), refrigerator.get(), false);

                foodRepository.save(food);
            }

            cnt++;

        }
        // TODO: 완료되면 알림처리
        return cnt;
    }
}
