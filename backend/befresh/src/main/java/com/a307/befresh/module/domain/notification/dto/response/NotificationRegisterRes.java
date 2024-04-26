package com.a307.befresh.module.domain.notification.dto.response;

import lombok.Builder;

@Builder
public record NotificationRegisterRes(
        String token,
        String title,
        String body
) {
}
