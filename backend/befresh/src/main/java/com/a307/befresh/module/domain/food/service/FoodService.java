package com.a307.befresh.module.domain.food.service;

import com.a307.befresh.module.domain.food.dto.request.FoodRegisterReqList;
import com.a307.befresh.module.domain.food.dto.response.FoodDetailRes;
import com.a307.befresh.module.domain.food.dto.response.FoodFailRes;
import com.a307.befresh.module.domain.food.dto.response.FoodListDetailRes;
import java.util.List;

public interface FoodService {

    void blocking() throws InterruptedException;

    void registerFood(FoodRegisterReqList foodRegisterReq);

    List<FoodListDetailRes> getFoodList(Long refrigeratorId);

    FoodDetailRes getFoodDetail(long foodId);

    List<FoodFailRes> getFoodFailList(long refrigeratorId);
}
