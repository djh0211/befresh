package com.a307.befresh.module.domain.food.dto.request;

import java.util.List;
import lombok.Builder;

@Builder
public record FoodRegisterReqList (
    long refrigeratorId,
    List<FoodRegisterReq> foodList
){ }
