package com.a307.befresh.module.domain.member.repository;

import com.a307.befresh.module.domain.member.Member;
import com.a307.befresh.module.domain.member.dto.response.MemberDetailRes;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByMemberId(String id);

    Optional<Member> findByRefreshToken(String refreshToken);

    boolean existsByMemberId(String id);
}
