package com.a307.befresh.module.domain.member.service;

import com.a307.befresh.module.domain.member.Member;
import com.a307.befresh.module.domain.member.dto.request.MemberSignupReq;
import com.a307.befresh.module.domain.refrigerator.Refrigerator;
import com.a307.befresh.module.domain.refrigerator.repository.RefrigeratorRepository;
import java.util.Optional;
import com.a307.befresh.module.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final RefrigeratorRepository refrigeratorRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public String registerMember(MemberSignupReq memberSignupReq) {

        Optional<Refrigerator> refrigerator = refrigeratorRepository.findById(
            memberSignupReq.refrigeratorId());

        if (refrigerator.isEmpty()) {
            return null;
        }

        Member member = Member.createMember(memberSignupReq.id(), memberSignupReq.password(),
            refrigerator.get());
        member.encodePassword(passwordEncoder);

        member.setRegUserSeq(1L);
        member.setModUserSeq(1L);

        memberRepository.save(member);

        member.setRegUserSeq(member.getId());
        member.setModUserSeq(member.getId());

        memberRepository.save(member);

        return member.getMemberId();
    }


}
