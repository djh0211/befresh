package com.a307.befresh.module.domain.notification;

import com.a307.befresh.module.domain.BaseEntity;
import com.a307.befresh.module.domain.member.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SequenceGenerator(name = "notification_seq", sequenceName = "notification_seq", allocationSize = 50, initialValue = 1)
public class Notification extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "notification_seq")
    private Long notificationId;

    @Column(nullable = false, length = 10)
    private String category;

    @Column(nullable = false, length = 300)
    private String message;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;
}
