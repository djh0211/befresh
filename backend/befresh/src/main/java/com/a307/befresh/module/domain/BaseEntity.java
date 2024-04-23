package com.a307.befresh.module.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {
    @Column(nullable = false)
    private boolean delYn;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime regDttm;

    @CreatedBy
    @Column(nullable = false, updatable = false)
    private Long regUserSeq;    // TODO : 자동 주입 추후 구현

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime modDttm;

    @LastModifiedBy
    @Column(nullable = false)
    private Long modUserSeq;    // TODO : 자동 주입 추후 구현

    @PrePersist
    public void prePersist() {
        LocalDateTime now = LocalDateTime.now();
        if (regDttm == null) { // only set if not already set
            regDttm = now;
        }
        modDttm = now;
    }

    @PreUpdate
    public void preUpdate() {
        modDttm = LocalDateTime.now();
    }
}
