package com.a307.befresh.module.domain.food.dto.request;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record FoodUpdateReq(
    long foodId,
    String name,
    LocalDate expirationDate
) {
}
