package com.a307.befresh.module.domain.food.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;

@Service
public class FoodRepositoryImpl implements FoodRepositoryCustom {

    private final JPAQueryFactory queryFactroy;

    public FoodRepositoryImpl(EntityManager em) {
        this.queryFactroy = new JPAQueryFactory(em);
    }

}
