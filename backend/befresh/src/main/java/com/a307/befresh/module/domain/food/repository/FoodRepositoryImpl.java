package com.a307.befresh.module.domain.food.repository;

import com.a307.befresh.module.domain.food.Food;
import com.a307.befresh.module.domain.food.QFood;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.a307.befresh.module.domain.food.QFood.*;

@Service
public class FoodRepositoryImpl implements FoodRepositoryCustom {

    private final JPAQueryFactory queryFactroy;

    public FoodRepositoryImpl(EntityManager em) {
        this.queryFactroy = new JPAQueryFactory(em);
    }

    @Override
    public List<Food> findFailFood(long refrigeratorId){
        return queryFactroy
                .selectFrom(food)
                .where(food.refrigerator.id.eq(refrigeratorId), food.missRegistered)
                .fetch();
    }
}
