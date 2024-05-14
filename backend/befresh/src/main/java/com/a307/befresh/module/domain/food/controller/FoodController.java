package com.a307.befresh.module.domain.food.controller;

import com.a307.befresh.global.api.response.BaseResponse;
import com.a307.befresh.global.config.batch.BatchScheduleConfig;
import com.a307.befresh.global.config.batch.FoodExpireBatchConfig;
import com.a307.befresh.global.config.batch.FoodSensorBatchConfig;
import com.a307.befresh.global.exception.code.SuccessCode;
import com.a307.befresh.global.security.domain.UserDetailsImpl;
import com.a307.befresh.module.domain.food.dto.request.FoodRegisterReqList;
import com.a307.befresh.module.domain.food.dto.request.FoodUpdateReq;
import com.a307.befresh.module.domain.food.dto.response.FoodDetailRes;
import com.a307.befresh.module.domain.food.dto.response.FoodFailRes;
import com.a307.befresh.module.domain.food.dto.response.FoodListDetailRes;
import com.a307.befresh.module.domain.food.service.FoodService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/foods")
@RequiredArgsConstructor
@CrossOrigin("*")
@Slf4j
public class FoodController {

    private final FoodService foodService;
    private final BatchScheduleConfig batchScheduleConfig;
    private final JobLauncher jobLauncher;
    private final FoodSensorBatchConfig foodSensorBatchConfig;
    private final FoodExpireBatchConfig foodExpireBatchConfig;

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
            @AuthenticationPrincipal UserDetailsImpl userDetails) {

        List<FoodListDetailRes> foodListDetailResList = foodService.getFoodList(userDetails.getRefrigeratorId());

        return BaseResponse.success(SuccessCode.SELECT_SUCCESS, foodListDetailResList);
    }

    @PutMapping()
    public ResponseEntity<BaseResponse<Long>> updateFood(@RequestBody FoodUpdateReq foodUpdateReq) {

        foodService.updateFood(foodUpdateReq);

        return BaseResponse.success(SuccessCode.UPDATE_SUCCESS, foodUpdateReq.foodId());
    }

    @DeleteMapping()
    public ResponseEntity<BaseResponse<Long>> deleteFood(@RequestParam Long foodId) {

        foodService.removeFood(foodId);

        return BaseResponse.success(SuccessCode.DELETE_SUCCESS, foodId);
    }

    @GetMapping("/detail")
    public ResponseEntity<BaseResponse<FoodDetailRes>> getFoodDetail(@RequestParam Long foodId) {

        FoodDetailRes foodDetail = foodService.getFoodDetail(foodId);

        return BaseResponse.success(SuccessCode.SELECT_SUCCESS, foodDetail);
    }

    @GetMapping("/fail")
    public ResponseEntity<BaseResponse<List<FoodFailRes>>> getFoodFailList(@RequestParam Long refrigeratorId) {

        List<FoodFailRes> foodFailList = foodService.getFoodFailList(refrigeratorId);

        return BaseResponse.success(SuccessCode.SELECT_SUCCESS, foodFailList);
    }

    @GetMapping("/batch/expire")
    public ResponseEntity<BaseResponse<Long>> processBatchExpire() {
        batchScheduleConfig.runExpiredFoodJob();

        return BaseResponse.success(SuccessCode.SELECT_SUCCESS, null);
    }

    @GetMapping("/batch/container")
    public ResponseEntity<BaseResponse<Long>> processBatchContainer() {
        batchScheduleConfig.runSensorJob();

        return BaseResponse.success(SuccessCode.SELECT_SUCCESS, null);
    }
}
