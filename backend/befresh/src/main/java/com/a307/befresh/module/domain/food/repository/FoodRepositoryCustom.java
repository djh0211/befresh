package com.a307.befresh.module.domain.food.repository;

import com.a307.befresh.module.domain.food.Food;

import java.time.LocalDate;
import java.util.List;

public interface FoodRepositoryCustom {
    List<Food> findFailFood(long refrigeratorId);

    List<Food> findExpireFood(long refrigeratorId, LocalDate targetDate);
}
