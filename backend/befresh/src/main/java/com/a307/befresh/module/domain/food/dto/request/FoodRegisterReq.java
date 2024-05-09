package com.a307.befresh.module.domain.food.dto.request;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record FoodRegisterReq(
    String name,
    LocalDate expirationDate,
    long ftypeId,

    String qrId

) {

}
