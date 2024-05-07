package com.a307.befresh.module.domain.container.repository;

import com.a307.befresh.module.domain.container.Container;
import com.a307.befresh.module.domain.container.QContainer;
import com.a307.befresh.module.domain.food.Food;
import com.a307.befresh.module.domain.food.repository.FoodRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import static com.a307.befresh.module.domain.container.QContainer.container;
import static com.a307.befresh.module.domain.food.QFood.food;
import static com.a307.befresh.module.domain.refrigerator.QRefrigerator.refrigerator;

@Service
public class ContainerRepositoryImpl implements ContainerRepositoryCustom {

    private final JPAQueryFactory queryFactroy;

    public ContainerRepositoryImpl(EntityManager em) {
        this.queryFactroy = new JPAQueryFactory(em);
    }

    @Override
    public List<Container> findBadFood() {
        return queryFactroy
                .selectFrom(container)
                .where(container.refresh.id.gt(container.prevRefresh.id))
                .join(container.refrigerator, refrigerator).fetchJoin()
                .fetch();
    }
}
