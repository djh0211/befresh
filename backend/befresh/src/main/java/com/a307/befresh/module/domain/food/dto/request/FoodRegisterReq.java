package com.a307.befresh.module.domain.food.dto.request;

import java.time.LocalDateTime;
import java.util.Date;
import lombok.Builder;

@Builder
public record FoodRegisterReq(
    String name,

    LocalDateTime expirationDate,
    long ftypeId,

    Double temperature,

    Double humidity,
    Double zCoordinate,

    Long qrId

) {

}
