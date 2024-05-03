package com.a307.befresh.module.domain.container.repository;

import com.a307.befresh.module.domain.container.Container;
import com.a307.befresh.module.domain.food.Food;

import java.time.LocalDate;
import java.util.List;

public interface ContainerRepositoryCustom {
    List<Container> findBadFood();
}
