package com.a307.befresh.module.domain.member.repository;

import com.a307.befresh.module.domain.member.Member;
import com.a307.befresh.module.domain.refrigerator.Refrigerator;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    List<Member> findByRefrigerator(Refrigerator refrigerator);

    Optional<Member> findByMemberId(String id);

    Optional<Member> findByRefreshToken(String refreshToken);

    boolean existsByMemberId(String id);
}
