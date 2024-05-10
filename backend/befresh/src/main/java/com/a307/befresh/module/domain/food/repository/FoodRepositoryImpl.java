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
    public List<Long> findDangerChangedFood(){
        return queryFactroy
                .select(food.foodId)
                .from(food)
                .where((food.expirationDate.before(LocalDate.now())))
                .join(food.refresh, refresh).fetchJoin()
                .join(food.refrigerator, refrigerator).fetchJoin()
                .join(refrigerator.memberSet, member).fetchJoin()
                .join(member.memberTokenSet, memberToken).fetchJoin()
                .fetch();
    }
}
