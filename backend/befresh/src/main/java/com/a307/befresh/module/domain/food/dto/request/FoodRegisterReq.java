package com.a307.befresh.module.domain.food.dto.request;

import java.time.LocalDateTime;
import java.util.Date;

public record FoodRegisterReq(
    String name,
    LocalDateTime expirationDate,
    boolean isOcr,
    long refrigerator_id
) {}
