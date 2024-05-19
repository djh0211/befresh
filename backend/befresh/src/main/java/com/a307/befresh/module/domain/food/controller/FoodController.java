package com.a307.befresh.module.domain.food.controller;

import com.a307.befresh.global.api.response.BaseResponse;
import com.a307.befresh.global.config.batch.BatchScheduleConfig;
import com.a307.befresh.global.config.batch.FoodExpireBatchConfig;
import com.a307.befresh.global.config.batch.FoodSensorBatchConfig;
import com.a307.befresh.global.exception.code.SuccessCode;
import com.a307.befresh.global.security.domain.UserDetailsImpl;
import com.a307.befresh.module.domain.food.Food;
import com.a307.befresh.module.domain.food.dto.request.FoodRegisterReq;
import com.a307.befresh.module.domain.food.dto.request.FoodRegisterReqList;
import com.a307.befresh.module.domain.food.dto.request.FoodUpdateReq;
import com.a307.befresh.module.domain.food.dto.response.FoodDetailRes;
import com.a307.befresh.module.domain.food.dto.response.FoodFailRes;
import com.a307.befresh.module.domain.food.dto.response.FoodListDetailRes;
import com.a307.befresh.module.domain.food.repository.FoodRepository;
import com.a307.befresh.module.domain.food.service.FoodService;
import com.a307.befresh.module.domain.notification.service.NotificationService;
import com.a307.befresh.module.domain.refresh.repository.RefreshRepository;
import java.time.LocalDate;
import java.util.ArrayList;
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
    private final FoodRepository foodRepository;
    private final RefreshRepository refreshRepository;
    private final NotificationService notificationService;

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

    @GetMapping("/tmp/register")
    public ResponseEntity<BaseResponse<Long>> tmp1() {
        FoodRegisterReq food1 = FoodRegisterReq.builder()
            .name("상추")
            .expirationDate(LocalDate.now().minusDays(1L))
            .ftypeId(2L)
            .build();

        FoodRegisterReq food2 = FoodRegisterReq.builder()
            .name("소고기")
            .expirationDate(LocalDate.now().plusDays(3L))
            .ftypeId(1L)
            .qrId("2219")
            .build();

        List<FoodRegisterReq> list = new ArrayList<>();
        list.add(food1);
        list.add(food2);

        FoodRegisterReqList foodRegisterReqList = FoodRegisterReqList.builder()
            .refrigeratorId(100)
            .foodList(list)
            .build();

        foodService.registerFood(foodRegisterReqList);

        return BaseResponse.success(SuccessCode.SELECT_SUCCESS, null);
    }

    @GetMapping("/tmp/1hour")
    public ResponseEntity<BaseResponse<Long>> tmp2() {
        Food food = foodRepository.findFoodByName_AndRefrigerator_Id("소고기", 100L);
        food.setRefresh(refreshRepository.findById(2L).get());
        Food save = foodRepository.save(food);

        List<Food> list = new ArrayList<>();
        list.add(save);

        notificationService.sendContainerNotification(list, "warn");

        return BaseResponse.success(SuccessCode.SELECT_SUCCESS, null);
    }

    @GetMapping("/tmp/2hour")
    public ResponseEntity<BaseResponse<Long>> tmp3() {
        Food food = foodRepository.findFoodByName_AndRefrigerator_Id("소고기", 100L);
        food.setRefresh(refreshRepository.findById(3L).get());
        Food save = foodRepository.save(food);

        List<Food> list = new ArrayList<>();
        list.add(save);

        notificationService.sendContainerNotification(list, "danger");

        return BaseResponse.success(SuccessCode.SELECT_SUCCESS, null);
    }

    @GetMapping("/tmp/12hour")
    public ResponseEntity<BaseResponse<Long>> tmp4() {
        Food food = foodRepository.findFoodByName_AndRefrigerator_Id("소고기", 100L);
        food.setRefresh(refreshRepository.findById(5L).get());
        Food save = foodRepository.save(food);

        List<Food> list = new ArrayList<>();
        list.add(save);

        notificationService.sendContainerNotification(list, "noUpdate");

        return BaseResponse.success(SuccessCode.SELECT_SUCCESS, null);
    }

    @GetMapping("/tmp/1day")
    public ResponseEntity<BaseResponse<Long>> tmp5() {
        Food food = foodRepository.findFoodByName_AndRefrigerator_Id("상추", 100L);
        food.setRefresh(refreshRepository.findById(3L).get());
        Food save = foodRepository.save(food);

        List<Food> list = new ArrayList<>();
        list.add(save);

        notificationService.sendExpireNotification(list, "danger");

        return BaseResponse.success(SuccessCode.SELECT_SUCCESS, null);
    }
}
