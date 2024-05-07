package com.a307.befresh.module.domain.food.dto.request;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record FoodRegisterReq(
    String name,

    String image,
    LocalDate expirationDate,
    long ftypeId,

    Double temperature,

    Double humidity,
    Double zCoordinate,

    String qrId

) {

}
