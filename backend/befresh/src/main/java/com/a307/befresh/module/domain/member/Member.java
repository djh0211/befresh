package com.a307.befresh.module.domain.member;

import com.a307.befresh.module.domain.BaseEntity;
import com.a307.befresh.module.domain.refrigerator.Refrigerator;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@Setter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SequenceGenerator(name = "member_seq", sequenceName = "member_seq", allocationSize = 50, initialValue = 1)
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "member_id", nullable = false, length = 30)
    private Long id;

    @Column(name = "id")
    private String memberId;

    @Column(length = 100)
    @Setter
    private String password;

    @Column(name ="refresh_token")
    private String refreshToken;

    //     Member - Refrigerator 연관 관계
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "refrigerator_id")
    private Refrigerator refrigerator;

    public void updateRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public void destroyRefreshToken() {
        this.refreshToken = null;
    }

    public void encodePassword(PasswordEncoder passwordEncoder){
        this.password = passwordEncoder.encode(password);
    }

    public static Member createMember(String member_id, String password, Refrigerator refrigerator) {

        Member member = new Member();

        member.setMemberId(member_id);
        member.setPassword(password);
        member.setRefrigerator(refrigerator);
        return member;
    }
}

