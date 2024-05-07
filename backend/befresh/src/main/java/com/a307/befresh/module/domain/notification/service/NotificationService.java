package com.a307.befresh.module.domain.notification.service;

import com.a307.befresh.module.domain.food.Food;
import com.a307.befresh.module.domain.member.Member;
import com.a307.befresh.module.domain.member.dto.request.MemberTokenReq;

import java.util.List;

public interface NotificationService {
    void sendExpireNotification(long refrigeratorId, List<Food> foodList, int daysBefore);

    void sentTempNotification1(String fcmToken);

    void sentTempNotification2(String fcmToken);

}
