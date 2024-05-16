package com.a307.befresh.module.domain.notification.dto.request;

public record NotificationTmpReq(
        long refrigeratorId,
        String title,
        String body,
        String category
) {
}
