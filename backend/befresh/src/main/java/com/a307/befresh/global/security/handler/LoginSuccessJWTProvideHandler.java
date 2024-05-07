package com.a307.befresh.global.security.handler;

import com.a307.befresh.global.api.response.BaseResponse;
import com.a307.befresh.global.exception.code.SuccessCode;
import com.a307.befresh.global.security.domain.dto.LoginDto;
import com.a307.befresh.global.security.jwt.JwtService;
import com.a307.befresh.module.domain.member.Member;
import com.a307.befresh.module.domain.member.repository.MemberRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

@Slf4j
@RequiredArgsConstructor
public class LoginSuccessJWTProvideHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final JwtService jwtService;
    private final MemberRepository memberRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
        Authentication authentication)
        throws IOException {

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        String memberId = userDetails.getUsername();
        Member member = memberRepository.findByMemberId(memberId).orElseThrow();

        Long id = member.getId();
        Long refrigeratorId = member.getRefrigerator().getId();

        String accessToken = jwtService.createAccessToken(id, refrigeratorId);
        String refreshToken = jwtService.createRefreshToken();

        jwtService.sendAccessAndRefreshToken(response, accessToken, refreshToken);
        jwtService.updateRefreshToken(id, refreshToken);

        log.info("로그인에 성공합니다. id: {}", id);
        log.info("AccessToken 을 발급합니다. AccessToken: {}", accessToken);
        log.info("RefreshToken 을 발급합니다. RefreshToken: {}", refreshToken);

        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");

        response.getWriter().write("{\n"
            + "    \"result\": " + "\"token 생성 성공\", \n"
            + "    \"status\": " + SuccessCode.INSERT_SUCCESS.getStatus() + ",\n"
            + "    \"message\": \"" + SuccessCode.INSERT_SUCCESS.getMessage() + "\"\n"
            + "}");
    }

}