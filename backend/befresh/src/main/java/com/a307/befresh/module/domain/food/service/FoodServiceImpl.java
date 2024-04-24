package com.a307.befresh.module.domain.food.service;

import com.a307.befresh.module.domain.food.Food;
import com.a307.befresh.module.domain.food.dto.request.FoodRegisterReq;
import com.a307.befresh.module.domain.food.repository.FoodRepository;
import com.a307.befresh.module.domain.refrigerator.Refrigerator;
import com.a307.befresh.module.domain.refrigerator.repository.RefrigeratorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class FoodServiceImpl implements FoodService{

    private final FoodRepository foodRepository;
    private final RefrigeratorRepository refrigeratorRepository;
    @Override
    public void blocking() throws InterruptedException {
        Thread.sleep(5000);
    }

    @Override
    public String registerFood(FoodRegisterReq foodRegisterReq, long member) {
        Refrigerator refrigerator = refrigeratorRepository.findById(
            foodRegisterReq.refrigerator_id()).get();

        Food food = Food.createFood(foodRegisterReq.name(), foodRegisterReq.expirationDate(), false,refrigerator);

        foodRepository.save(food);
        return food.getName();
    }
}
