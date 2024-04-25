package com.a307.befresh.module.domain.refresh;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SequenceGenerator(name = "refresh_seq", sequenceName = "refresh_seq", allocationSize = 1, initialValue = 1)
public class Refresh {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "refresh_seq")
    @Column(name = "refresh_id", nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;
}