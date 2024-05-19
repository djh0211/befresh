package com.a307.befresh.module.domain.food.repository;

import com.a307.befresh.module.domain.food.Food;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<Food, Long>, FoodRepositoryCustom {

    List<Food> findByRefrigerator_Id(Long refrigeratorId);

    Food findFoodByName_AndRefrigerator_Id(String name, long refrigeratorId);
}
