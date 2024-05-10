package com.a307.befresh.module.domain.notification.repository;

import com.a307.befresh.module.domain.food.Food;
import com.a307.befresh.module.domain.notification.Notification;
import com.a307.befresh.module.domain.notification.QNotification;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import static com.a307.befresh.module.domain.food.QFood.food;
import static com.a307.befresh.module.domain.notification.QNotification.notification;

@Service
public class NotificationRepositoryImpl implements NotificationRepositoryCustom {

    private final JPAQueryFactory queryFactroy;

    public NotificationRepositoryImpl(EntityManager em) {
        this.queryFactroy = new JPAQueryFactory(em);
    }

    @Override
    public List<Notification> findNotificationList(long refrigeratorId) {
        return queryFactroy
                .selectFrom(notification)
                .where(notification.refrigerator.id.eq(refrigeratorId))
                .orderBy(notification.regDttm.desc())
                .fetch();
    }
}
