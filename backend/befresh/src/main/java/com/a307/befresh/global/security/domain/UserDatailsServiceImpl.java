package com.a307.befresh.global.security.domain;

import static com.a307.befresh.global.exception.code.ErrorCode.NOT_FOUND_USER_EXCEPTION;

import com.a307.befresh.global.exception.BaseExceptionHandler;
import com.a307.befresh.module.domain.member.Member;
import com.a307.befresh.module.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDatailsServiceImpl implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        Member member = memberRepository.findByMemberId(id)
            .orElseThrow(() -> new BaseExceptionHandler(NOT_FOUND_USER_EXCEPTION));
        return new UserDetailsImpl(member);
    }
}
