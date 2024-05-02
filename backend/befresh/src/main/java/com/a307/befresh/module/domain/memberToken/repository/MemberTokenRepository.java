package com.a307.befresh.module.domain.memberToken.repository;

import com.a307.befresh.module.domain.memberToken.MemberToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberTokenRepository extends JpaRepository<MemberToken, Long> {
    Optional<MemberToken> findByToken(String token);
}
