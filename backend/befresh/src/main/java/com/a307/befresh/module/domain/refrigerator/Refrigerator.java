package com.a307.befresh.module.domain.refrigerator;

import com.a307.befresh.module.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SequenceGenerator(name = "refrigerator_seq", sequenceName = "refrigerator_seq", allocationSize = 50, initialValue = 1)
public class Refrigerator extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "refrigerator_seq")
    @Column(name = "refrigerator_id", nullable = false)
    private Long id;
}