package com.a307.befresh.module.domain.notification.service;

import com.a307.befresh.module.domain.food.Food;
import com.a307.befresh.module.domain.notification.dto.request.NotificationTmpReq;
import com.a307.befresh.module.domain.notification.dto.response.NotificationDetailRes;
import com.a307.befresh.module.domain.refrigerator.Refrigerator;

import java.util.List;

public interface NotificationService {
    List<NotificationDetailRes> getNotificationList(long refrigeratorId);

    void sendRegisterNotification(Refrigerator refrigerator);

    void sendRegisterErrorNotification(Refrigerator refrigerator);

    void deleteNotidication(Long notificationId);

    void sendNotification(List<Food> foodList, String category);

    void sendTmpNotification(NotificationTmpReq notificationTmpReq);

    int deleteAllNotification(long refrigeratorId);
}
