package com.a307.befresh.module.domain.food.repository;

import com.a307.befresh.module.domain.food.Food;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import static com.a307.befresh.module.domain.food.QFood.food;
import static com.a307.befresh.module.domain.member.QMember.member;
import static com.a307.befresh.module.domain.memberToken.QMemberToken.memberToken;
import static com.a307.befresh.module.domain.refresh.QRefresh.refresh;
import static com.a307.befresh.module.domain.refrigerator.QRefrigerator.refrigerator;

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

    @Override
    public List<Food> findWarnFood(){
        return queryFactroy
                .select(food)
                .from(food)
                .where(food.refresh.id.eq(1L),
                        food.expirationDate.isNotNull())
                .fetch();
    }

    @Override
    public List<Long> findDangerFood(){
        return queryFactroy
                .select(food.foodId)
                .from(food)
                .where((food.expirationDate.before(LocalDate.now())),
                        food.refresh.id.ne(3L))
                .fetch();
    }

    @Override
    public List<Food> findUpdateFood(List<Long> foodIdList) {
        return queryFactroy
                .selectFrom(food)
                .where(food.foodId.in(foodIdList))
                .join(food.refresh, refresh).fetchJoin()
                .fetch();
    }

    @Override
    public List<Food> findNotiFood(List<Long> foodIdList) {
        return queryFactroy
                .selectFrom(food)
                .where(food.foodId.in(foodIdList))
                .join(food.refresh, refresh).fetchJoin()
                .join(food.refrigerator, refrigerator).fetchJoin()
                .join(refrigerator.memberSet, member).fetchJoin()
                .join(member.memberTokenSet, memberToken).fetchJoin()
                .fetch();
    }
}
