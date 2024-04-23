package com.a307.befresh.module.domain.container;

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
@SequenceGenerator(name = "container_seq", sequenceName = "container_seq", allocationSize = 50, initialValue = 1)
public class Container extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "container_seq")
    private Long containerId;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(length = 10)
    private String freshState;

    @Column(columnDefinition = "Number(3, 1)")
    private Double temperature;

    @Column(columnDefinition = "Number(4, 1)")
    private Double humidity;

    @Column
    private LocalDateTime expirationDate;

    @Column(nullable = false)
    private boolean isUse;

    @Column(columnDefinition = "Number(5, 2)")
    private Double zCoordinate;

    @ManyToOne
    @JoinColumn(name = "refrigerator_id", nullable = false)
    private Refrigerator refrigerator;
}
