package com.a307.befresh.module.domain.container.repository;

import com.a307.befresh.module.domain.container.QContainer;
import com.a307.befresh.module.domain.food.Food;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static com.a307.befresh.module.domain.container.QContainer.container;
import static com.a307.befresh.module.domain.food.QFood.food;
import static com.a307.befresh.module.domain.member.QMember.member;
import static com.a307.befresh.module.domain.memberToken.QMemberToken.memberToken;
import static com.a307.befresh.module.domain.refresh.QRefresh.refresh;
import static com.a307.befresh.module.domain.refrigerator.QRefrigerator.refrigerator;

@Service
public class ContainerRepositoryImpl implements ContainerRepositoryCustom {

    private final JPAQueryFactory queryFactroy;

    public ContainerRepositoryImpl(EntityManager em) {
        this.queryFactroy = new JPAQueryFactory(em);
    }

    @Override
    public List<Long> findFreshContainer(){
        return queryFactroy
                .select(container.foodId)
                .from(container)
                .where(container.refresh.id.in(2L, 3L, 4L),
                        container.pH.lt(5.9))
                .fetch();
    }


    @Override
    public List<Long> findWarnContainer(){
        return queryFactroy
                .select(container.foodId)
                .from(container)
                .where(container.refresh.id.in(1L, 4L),
                        container.pH.between(5.9, 6.1))
                .fetch();
    }

    @Override
    public List<Long> findDangerContainer(){
        return queryFactroy
                .select(container.foodId)
                .from(container)
                .where(container.refresh.id.in(1L, 2L, 4L),
                        container.pH.gt(6.1))
                .fetch();
    }

    @Override
    public List<Long> findNoUpdateContainer(){
        return queryFactroy
                .select(container.foodId)
                .from(container)
                .where(container.refresh.id.ne(5L),
                        container.modDttm.lt(LocalDateTime.now().minusHours(3)))
                .fetch();
    }

    @Override
    public List<Long> findReUpdateContainer(){
        return queryFactroy
                .select(container.foodId)
                .from(container)
                .where(container.refresh.id.eq(5L),
                        container.modDttm.goe(LocalDateTime.now().minusHours(3)))
                .fetch();
    }
}
