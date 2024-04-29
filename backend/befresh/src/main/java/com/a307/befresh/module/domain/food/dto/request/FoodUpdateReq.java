package com.a307.befresh.module.domain.food.dto.request;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record FoodUpdateReq(
    long foodId,
    String name,
    LocalDateTime expirationDate
) {
}
