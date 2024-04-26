package com.a307.befresh.module.domain.member.dto.request;

public record MemberSignupReq(

    String id,
    String password,

    Long refrigeratorId
) {

}
