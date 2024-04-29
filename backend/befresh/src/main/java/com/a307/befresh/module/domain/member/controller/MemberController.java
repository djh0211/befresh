package com.a307.befresh.module.domain.member.controller;

import com.a307.befresh.global.api.response.BaseResponse;
import com.a307.befresh.global.exception.code.SuccessCode;
import com.a307.befresh.module.domain.food.dto.request.FoodRegisterReqList;
import com.a307.befresh.module.domain.member.dto.request.MemberSignupReq;
import com.a307.befresh.module.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
@CrossOrigin("*")
@Slf4j
public class MemberController {

    private final MemberService memberService;
    @PostMapping("/signup")
    public ResponseEntity<BaseResponse<String>> registerFood(
        @RequestBody MemberSignupReq memberSignupReq) {

        String id = memberService.registerMember(memberSignupReq);

        return BaseResponse.success(SuccessCode.INSERT_SUCCESS, id);
    }
}
