package com.a307.befresh.module.domain.food.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record FoodDetailRes(
        Long id,
        String name,
        String image,
        LocalDateTime expirationDate,
        LocalDateTime regDttm,
        Integer elapsedTime,
        String refresh,
        String ftype,
        Double freshState,
        Double temperature,
        Double humidity
) {
}
