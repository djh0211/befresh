package com.a307.befresh.module.domain.notification.controller;

import com.a307.befresh.global.api.response.BaseResponse;
import com.a307.befresh.global.exception.code.SuccessCode;
import com.a307.befresh.module.domain.member.dto.request.MemberTokenReq;
import com.a307.befresh.module.domain.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notification")
@RequiredArgsConstructor
@CrossOrigin("*")
@Slf4j
public class NotificationController {

    private final NotificationService notificationService;
    @GetMapping("/1")
    public ResponseEntity<BaseResponse<String>> getTmpNotification1(
            @RequestBody MemberTokenReq memberTokenReq) {

        notificationService.sentTempNotification1(memberTokenReq.fcmToken());

        return BaseResponse.success(SuccessCode.SELECT_SUCCESS, memberTokenReq.fcmToken());
    }

    @GetMapping("/2")
    public ResponseEntity<BaseResponse<String>> getTmpNotification2(
            @RequestBody MemberTokenReq memberTokenReq) {

        notificationService.sentTempNotification2(memberTokenReq.fcmToken());

        return BaseResponse.success(SuccessCode.SELECT_SUCCESS, memberTokenReq.fcmToken());
    }
}