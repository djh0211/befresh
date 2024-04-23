package com.a307.befresh.module.domain.member;

import com.a307.befresh.module.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "member_id", nullable = false)
    private int id;

    @Column(length = 30)
    @Setter
    private String password;

    // Member - Refrigerator 연관 관계
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "member_id")
//    private List<Refrigerator> refrigeratorList = new ArrayList<>();

}

