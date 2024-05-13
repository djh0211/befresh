package com.a307.befresh.module.domain.notification;

import com.a307.befresh.module.domain.BaseEntity;
import com.a307.befresh.module.domain.refrigerator.Refrigerator;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SequenceGenerator(name = "notification_seq", sequenceName = "notification_seq", allocationSize = 50, initialValue = 1)
public class Notification extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "notification_seq")
    private Long notificationId;

    @Column(nullable = false, length = 10)
    @Setter
    private String category;

    @Column(length = 300)
    private String title;

    @Column(nullable = false, length = 300)
    private String message;

    @ManyToOne
    @JoinColumn(name = "refrigerator_id", nullable = false)
    private Refrigerator refrigerator;

    public static Notification createNotification(String category, String title, String message, Refrigerator refrigerator) {
        Notification notification = new Notification();
        notification.setCategory(category);
        notification.setTitle(title);
        notification.setMessage(message);
        notification.setRefrigerator(refrigerator);
        notification.setRegUserSeq(refrigerator.getId());
        notification.setModUserSeq(refrigerator.getId());

        return notification;
    }
}
