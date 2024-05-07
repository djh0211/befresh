package com.a307.befresh.global.security.jwt;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Optional;

public interface JwtService {

    String createAccessToken(Long id, Long refrigeratorId);

    String createRefreshToken();

    void updateRefreshToken(Long id, String refreshToken);

    void destroyRefreshToken(String id);

    void sendAccessAndRefreshToken(HttpServletResponse response, String accessToken,
        String refreshToken);

    void sendAccessToken(HttpServletResponse response, String accessToken);

    Optional<String> extractAccessToken(HttpServletRequest request);

    Optional<String> extractRefreshToken(HttpServletRequest request);

    Optional<Long> extractId(String accessToken);

    Optional<String> extractRefrigeratorId(String accessToken);

    void setAccessTokenHeader(HttpServletResponse response, String accessToken);

    void setRefreshTokenHeader(HttpServletResponse response, String refreshToken);

    boolean isTokenValid(String token);

}
