package com.a307.befresh.global.security.filter;

import com.a307.befresh.global.api.response.ErrorResponse;
import com.a307.befresh.global.exception.BaseExceptionHandler;
import com.a307.befresh.global.exception.code.ErrorCode;
import com.a307.befresh.global.security.domain.UserDetailsImpl;
import com.a307.befresh.global.security.jwt.JwtService;
import com.a307.befresh.module.domain.member.Member;
import com.a307.befresh.module.domain.member.repository.MemberRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.core.authority.mapping.NullAuthoritiesMapper;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationProcessingFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final MemberRepository memberRepository;

    private GrantedAuthoritiesMapper authoritiesMapper = new NullAuthoritiesMapper();//5

    private final String NO_CHECK_URL = "/api/login";//1

    /**
     * 1. 리프레시 토큰이 오는 경우 -> 유효하면 AccessToken 재발급후, 필터 진행 X, 바로 튕기기
     * <p>
     * 2. 리프레시 토큰은 없고 AccessToken만 있는 경우 -> 유저정보 저장후 필터 계속 진행
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
        FilterChain filterChain) throws ServletException, IOException {

        String path = request.getRequestURI();
        if (path.equals("/api/login") || request.getRequestURI().equals("/api/health")
            || request.getRequestURI().equals("/api/members/signup")) {
            filterChain.doFilter(request, response);
            return;
        }

        String refreshToken = jwtService
            .extractRefreshToken(request)
            .filter(jwtService::isTokenValid)
            .orElse(null);

        if (refreshToken != null) {
            checkRefreshTokenAndReIssueAccessToken(response, refreshToken);
            return;
        }

        checkAccessTokenAndAuthentication(request, response, filterChain);
    }

    private void checkAccessTokenAndAuthentication(HttpServletRequest request,
        HttpServletResponse response, FilterChain filterChain)
        throws ServletException, IOException {

        jwtService.extractAccessToken(request).filter(jwtService::isTokenValid).ifPresent(
            accessToken -> jwtService.extractId(accessToken).ifPresent(
                id -> memberRepository.findById(id).ifPresent(
                    member -> saveAuthentication(member)
                )
            )
        );

        filterChain.doFilter(request, response);
    }


    private void saveAuthentication(Member member) {

        UserDetails userDetails = new UserDetailsImpl(member);

        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null,
            authoritiesMapper.mapAuthorities(userDetails.getAuthorities()));

        SecurityContext context = SecurityContextHolder.getContext();
        context.setAuthentication(authentication);
    }

    private void checkRefreshTokenAndReIssueAccessToken(HttpServletResponse response,
        String refreshToken) throws IOException {

        Optional<Member> member = memberRepository.findByRefreshToken(refreshToken);

        if(!member.isPresent()) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN); //401 인증 실패
            return;
        }

        jwtService.setAccessTokenHeader(response, jwtService.createAccessToken(member.get().getId(), member.get().getRefrigerator().getId()));

        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");

    }
}
