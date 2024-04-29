package com.a307.befresh.global.security.handler;

import com.a307.befresh.global.api.response.ErrorResponse;
import com.a307.befresh.global.exception.code.ErrorCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

@Slf4j
public class LoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
        AuthenticationException exception)
        throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); //401 인증 실패
        response.getWriter().write(objectMapper.writeValueAsString(
            new ErrorResponse(ErrorCode.UNAUTHORIZED, ErrorCode.UNAUTHORIZED.getMessage())
        ));
        log.info("로그인에 실패하였습니다.");
    }
}