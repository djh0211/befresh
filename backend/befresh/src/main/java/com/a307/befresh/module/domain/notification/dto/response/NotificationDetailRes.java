package com.a307.befresh.module.domain.notification.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record NotificationDetailRes(
        Long notificationId,
        DataDto data,
        NotificationDto notification,
        LocalDateTime dateTime
) {
    @Builder
    public static record NotificationDto(
            String title,
            String body
    ){}

    @Builder
    public static record DataDto(
            String category
    ){}
}
