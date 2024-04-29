package com.a307.befresh.global.security.domain.dto;

import lombok.Builder;

@Builder
public record LoginDto (
    String id,
    String accessToken,
    String refreshToken
){
}
