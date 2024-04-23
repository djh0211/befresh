package com.a307.befresh.module.domain.memberToken;

import com.a307.befresh.module.domain.BaseEntity;
import com.a307.befresh.module.domain.member.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SequenceGenerator(name = "member_token_seq", sequenceName = "member_token_seq", allocationSize = 50, initialValue = 1)
public class MemberToken extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "member_token_seq")
    private Long memberTokenId;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Column(nullable = false, length = 300)
    private String token;
}
