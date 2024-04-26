package com.a307.befresh.module.domain.member.repository;

import com.a307.befresh.module.domain.member.Member;
import com.a307.befresh.module.domain.member.dto.response.MemberDetailRes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    MemberDetailRes findByMember_id(String id);
}
