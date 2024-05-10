package com.a307.befresh.module.domain.food.repository;

import com.a307.befresh.module.domain.food.Food;

import java.util.List;

public interface FoodRepositoryCustom {
    List<Food> findFailFood(long refrigeratorId);

    List<Long> findDangerChangedFood();
}
