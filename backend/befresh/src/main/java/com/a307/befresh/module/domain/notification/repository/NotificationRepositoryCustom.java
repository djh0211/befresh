package com.a307.befresh.module.domain.notification.repository;

import com.a307.befresh.module.domain.food.Food;
import com.a307.befresh.module.domain.notification.Notification;

import java.time.LocalDate;
import java.util.List;

public interface NotificationRepositoryCustom {
    List<Notification> findNotificationList(long refrigeratorId);

}
