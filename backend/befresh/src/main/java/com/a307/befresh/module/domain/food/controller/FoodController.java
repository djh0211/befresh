package com.a307.befresh.module.domain.food.controller;

import com.a307.befresh.global.api.response.BaseResponse;
import com.a307.befresh.global.exception.code.SuccessCode;
import com.a307.befresh.module.domain.food.dto.request.FoodRegisterReq;
import com.a307.befresh.module.domain.food.dto.request.FoodRegisterReqList;
import com.a307.befresh.module.domain.food.dto.response.FoodListDetailRes;
import com.a307.befresh.module.domain.food.service.FoodService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<BaseResponse<List<FoodListDetailRes>>> registerFood(
        @RequestParam Long refrigeratorId) {

        List<FoodListDetailRes> foodListDetailResList = foodService.getFoodList(refrigeratorId);

        return BaseResponse.success(SuccessCode.SELECT_SUCCESS, foodListDetailResList);
    }


}
