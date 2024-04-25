package com.a307.befresh.module.domain.food.service;

import com.a307.befresh.module.domain.food.dto.request.FoodRegisterReq;

public interface FoodService {
    void blocking() throws InterruptedException;

    String registerFood(FoodRegisterReq foodRegisterReq, long member);

}
