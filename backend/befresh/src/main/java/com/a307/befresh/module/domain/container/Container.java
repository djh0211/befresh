package com.a307.befresh.module.domain.container;

import com.a307.befresh.module.domain.BaseEntity;
import com.a307.befresh.module.domain.food.Food;
import com.a307.befresh.module.domain.refrigerator.Refrigerator;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Container extends Food {

    @Column(columnDefinition = "Number(3, 1)")
    private Double temperature;

    @Column(columnDefinition = "Number(4, 1)")
    private Double humidity;

    @Column(columnDefinition = "Number(5, 2)")
    private Double zCoordinate;

    @Column(name = "qr_id")
    private Long qrId;

}
