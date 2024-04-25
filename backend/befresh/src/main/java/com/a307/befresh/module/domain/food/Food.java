package com.a307.befresh.module.domain.food;

import com.a307.befresh.module.domain.BaseEntity;
import com.a307.befresh.module.domain.Ftype.Ftype;
import com.a307.befresh.module.domain.refresh.Refresh;
import com.a307.befresh.module.domain.refrigerator.Refrigerator;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import lombok.Setter;

@Getter
@Entity
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Inheritance(strategy = InheritanceType.JOINED)
@SequenceGenerator(name = "food_seq", sequenceName = "food_seq", allocationSize = 50, initialValue = 1)
public class Food extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "food_seq")
    private Long foodId;

    @Column(nullable = false, length = 100)
    private String name;

    @Column
    private LocalDateTime expirationDate;

    @ManyToOne
    @JoinColumn(name = "refresh_id")
    private Refresh refresh;

    @ManyToOne
    @JoinColumn(name = "ftype_id", nullable = false)
    private Ftype ftype;

    @ManyToOne
    @JoinColumn(name = "refrigerator_id", nullable = false)
    private Refrigerator refrigerator;

    public static Food createFood(String name, LocalDateTime expirationDate, Refresh refresh,
        Ftype ftype, Refrigerator refrigerator) {
        Food food = new Food();

        food.setName(name);
        food.setExpirationDate(expirationDate);
        food.setRefresh(refresh);
        food.setFtype(ftype);
        food.setRefrigerator(refrigerator);
        food.setRegUserSeq(1L);
        food.setModUserSeq(1L);

        return food;
    }
}
