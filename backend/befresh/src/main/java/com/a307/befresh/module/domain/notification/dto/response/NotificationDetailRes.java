package com.a307.befresh.module.domain.notification.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record NotificationDetailRes(
        String category,
        String message,
        LocalDateTime dateTime
) {
}
