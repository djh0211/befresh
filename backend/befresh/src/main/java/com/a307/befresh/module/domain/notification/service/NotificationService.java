package com.a307.befresh.module.domain.notification.service;

import com.a307.befresh.module.domain.container.Container;
import com.a307.befresh.module.domain.food.Food;

import java.util.List;

public interface NotificationService {
    void sendExpireNotification(long refrigeratorId, List<Food> foodList, int daysBefore);

    void sendContainerRefreshNotification(List<Container> containerList);
}
