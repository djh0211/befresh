package com.a307.befresh.module.domain.food.service;

import com.a307.befresh.module.domain.food.dto.request.FoodRegisterReqList;

public interface FoodService {
    void blocking() throws InterruptedException;

    void registerFood(FoodRegisterReqList foodRegisterReq);

}
