package com.a307.befresh.module.domain.member.service;

import com.a307.befresh.module.domain.member.Member;
import com.a307.befresh.module.domain.member.dto.request.MemberSignupReq;
import com.a307.befresh.module.domain.member.dto.response.MemberDetailRes;
import com.a307.befresh.module.domain.member.repository.MemberRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface MemberService {

    String registerMember(MemberSignupReq memberSignupReq);

}
