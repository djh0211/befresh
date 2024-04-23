package com.a307.befresh.module.domain.food;

import com.a307.befresh.module.domain.BaseEntity;
import com.a307.befresh.module.domain.refrigerator.Refrigerator;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SequenceGenerator(name = "food_seq", sequenceName = "food_seq", allocationSize = 50, initialValue = 1)
public class Food extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "food_seq")
    private Long foodId;

    @Column(nullable = false, length = 100)
    private String name;

    @Column
    private LocalDateTime expirationDate;

    @Column(nullable = false)
    private boolean isOcr;

    @ManyToOne
    @JoinColumn(name = "refrigerator_id", nullable = false)
    private Refrigerator refrigerator;
}
