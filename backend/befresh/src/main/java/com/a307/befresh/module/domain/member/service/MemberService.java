package com.a307.befresh.module.domain.member.service;

import com.a307.befresh.module.domain.member.Member;
import com.a307.befresh.module.domain.member.dto.request.MemberSignupReq;
import com.a307.befresh.module.domain.member.dto.request.MemberTokenReq;

public interface MemberService {

    String registerMember(MemberSignupReq memberSignupReq);

    Long registerFcmToken(MemberTokenReq memberTokenReq, Member member);
}
