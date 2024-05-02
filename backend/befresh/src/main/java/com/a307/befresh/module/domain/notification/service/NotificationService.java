package com.a307.befresh.module.domain.notification.service;

import com.a307.befresh.module.domain.food.Food;

import java.util.List;

public interface NotificationService {
    void sendFreshWarnMessage();

    void sendExpireNotification(long refrigeratorId, List<Food> foodList, int daysBefore);
}
