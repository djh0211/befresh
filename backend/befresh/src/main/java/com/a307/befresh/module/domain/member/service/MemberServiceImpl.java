package com.a307.befresh.module.domain.member.service;

import com.a307.befresh.module.domain.member.Member;
import com.a307.befresh.module.domain.member.dto.request.MemberSignupReq;
import com.a307.befresh.module.domain.member.dto.response.MemberDetailRes;
import com.a307.befresh.module.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;


    @Override
    public MemberDetailRes loadMemberById(String id) {
        return memberRepository.findByMember_id(id);
    }

    @Override
    public void registerMember(MemberSignupReq memberSignupReq) {



        Member member = Member.createMember(memberSignupReq.id(), memberSignupReq.password();

    }


}
