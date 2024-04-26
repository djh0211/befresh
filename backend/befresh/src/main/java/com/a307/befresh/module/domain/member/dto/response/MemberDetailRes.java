package com.a307.befresh.module.domain.member.dto.response;

import lombok.Builder;

@Builder
public record MemberDetailRes(

    String id,
    String password,

    Long refrigeratorId
) {

}
