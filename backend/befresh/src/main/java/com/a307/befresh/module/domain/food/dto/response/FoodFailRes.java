package com.a307.befresh.module.domain.food.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record FoodFailRes(
        Long id,
        String name,
        String image,
        LocalDateTime regDttm
) {

}
