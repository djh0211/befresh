package com.a307.befresh.global.security.domain.dto;

import lombok.Builder;

@Builder
public record LoginDto (
    Long id,
    String accessToken,
    String refreshToken
){
}
