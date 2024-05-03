package com.a307.befresh.module.domain.container.repository;

import com.a307.befresh.module.domain.container.Container;
import com.a307.befresh.module.domain.food.repository.FoodRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContainerRepository extends JpaRepository<Container, Long>, ContainerRepositoryCustom {
    Container findByQrId(long qrId);
}
