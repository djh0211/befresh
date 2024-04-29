package com.a307.befresh.module.domain.food.controller;

import com.a307.befresh.global.api.response.BaseResponse;
import com.a307.befresh.global.exception.code.SuccessCode;
import com.a307.befresh.module.domain.food.dto.request.FoodRegisterReqList;
import com.a307.befresh.module.domain.food.dto.response.FoodDetailRes;
import com.a307.befresh.module.domain.food.dto.response.FoodListDetailRes;
import com.a307.befresh.module.domain.food.service.FoodService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/foods")
@RequiredArgsConstructor
@CrossOrigin("*")
@Slf4j
public class FoodController {

    private final FoodService foodService;

    @GetMapping("/test/thread")
    public ResponseEntity<ThreadInfo> getThreadInfo() throws InterruptedException {
        foodService.blocking();
        return ResponseEntity.ok(
                new ThreadInfo(
                        Thread.currentThread().isVirtual(),
                        Thread.currentThread().toString())
        );
    }

    public record ThreadInfo(boolean isVirtual, String threadName) {

    }

    @PostMapping
    public ResponseEntity<BaseResponse<Integer>> registerFood(
            @RequestBody FoodRegisterReqList foodRegisterReqList) {

        foodService.registerFood(foodRegisterReqList);

        return BaseResponse.success(SuccessCode.INSERT_SUCCESS, null);
    }

    @GetMapping
    public ResponseEntity<BaseResponse<List<FoodListDetailRes>>> getFoodList(
            @RequestParam Long refrigeratorId) {

        List<FoodListDetailRes> foodListDetailResList = foodService.getFoodList(refrigeratorId);

        return BaseResponse.success(SuccessCode.SELECT_SUCCESS, foodListDetailResList);
    }

    @GetMapping("/detail")
    public ResponseEntity<BaseResponse<FoodDetailRes>> getFoodDetail(@RequestParam Long foodId) {

        FoodDetailRes foodDetail = foodService.getFoodDetail(foodId);

        return BaseResponse.success(SuccessCode.SELECT_SUCCESS, foodDetail);
    }


}
