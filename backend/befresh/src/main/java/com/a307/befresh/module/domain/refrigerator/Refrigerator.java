package com.a307.befresh.module.domain.refrigerator;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Refrigerator {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "refrigerator_seq")
    @SequenceGenerator(name = "refrigerator_seq", sequenceName = "refrigerator_seq", allocationSize = 1)
    private Long refrigeratorId;

}