package com.a307.befresh.module.domain.member.service;

import static com.a307.befresh.global.exception.code.ErrorCode.DUPLICATED_USER;
import static com.a307.befresh.global.exception.code.ErrorCode.NOT_FOUND_REFRIGERATOR_EXCEPTION;

import com.a307.befresh.global.exception.BaseExceptionHandler;
import com.a307.befresh.module.domain.member.Member;
import com.a307.befresh.module.domain.member.dto.request.MemberSignupReq;
import com.a307.befresh.module.domain.member.dto.request.MemberTokenReq;
import com.a307.befresh.module.domain.memberToken.MemberToken;
import com.a307.befresh.module.domain.memberToken.repository.MemberTokenRepository;
import com.a307.befresh.module.domain.refrigerator.Refrigerator;
import com.a307.befresh.module.domain.refrigerator.repository.RefrigeratorRepository;
import java.util.Optional;
import com.a307.befresh.module.domain.member.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final MemberTokenRepository memberTokenRepository;
    private final RefrigeratorRepository refrigeratorRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public String registerMember(MemberSignupReq memberSignupReq) {

        Optional<Refrigerator> refrigerator = refrigeratorRepository.findById(
            memberSignupReq.refrigeratorId());

        if (refrigerator.isEmpty()) {
            throw new BaseExceptionHandler(NOT_FOUND_REFRIGERATOR_EXCEPTION);
        }

        if(memberRepository.existsByMemberId(memberSignupReq.id())) {
            throw new BaseExceptionHandler(DUPLICATED_USER);
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

    @Override
    @Transactional
    public Long registerFcmToken(MemberTokenReq memberTokenReq, Member member) {
        String fcmToken = memberTokenReq.fcmToken();
        Optional<MemberToken> token = memberTokenRepository.findByToken(fcmToken);

        if(token.isEmpty()){
            Member member1 = memberRepository.findById(member.getId()).orElseThrow();
            MemberToken memberToken = MemberToken.createMemberToken(member1, fcmToken);
            memberTokenRepository.save(memberToken);
        }
        return member.getId();
    }
}
