package com.a307.befresh.module.domain.food.dto.response;

import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public record FoodListDetailRes(

    Long id,
    String name,

    LocalDateTime reg_dttm,

    int elapsed_time,

    String refresh,

    Double fresh_state
) {

}
