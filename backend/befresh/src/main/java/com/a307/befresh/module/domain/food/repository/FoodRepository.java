package com.a307.befresh.module.domain.food.repository;

import com.a307.befresh.module.domain.food.Food;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<Food, Long> {

}
