package com.a307.befresh.module.domain.food.controller;

import com.a307.befresh.global.api.response.BaseResponse;
import com.a307.befresh.global.exception.code.SuccessCode;
import com.a307.befresh.module.domain.food.dto.request.FoodRegisterReq;
import com.a307.befresh.module.domain.food.service.FoodService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public ResponseEntity<BaseResponse<String>> registerFood(
        @RequestBody FoodRegisterReq foodRegisterReq) {

        String food = foodService.registerFood(foodRegisterReq, 1L);

        return BaseResponse.success(SuccessCode.INSERT_SUCCESS, food);
    }

}
