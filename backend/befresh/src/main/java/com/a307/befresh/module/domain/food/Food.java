package com.a307.befresh.module.domain.food;

import com.a307.befresh.module.domain.BaseEntity;
import com.a307.befresh.module.domain.Ftype.Ftype;
import com.a307.befresh.module.domain.refresh.Refresh;
import com.a307.befresh.module.domain.refrigerator.Refrigerator;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

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
    private String image;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDate expirationDate;

    @Column(name = "miss_registered")
    private boolean missRegistered;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "refresh_id")
    private Refresh refresh;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ftype_id", nullable = false)
    private Ftype ftype;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "refrigerator_id", nullable = false)
    private Refrigerator refrigerator;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prev_refresh")
    private Refresh prevRefresh;

    public static Food createFood(String name, String image, LocalDate expirationDate, Refresh refresh,
        Ftype ftype, Refrigerator refrigerator, boolean missRegistered) {
        Food food = new Food();

        food.setName(name);
        food.setImage(image);
        food.setExpirationDate(expirationDate);
        food.setRefresh(refresh);
        food.setFtype(ftype);
        food.setRefrigerator(refrigerator);
        food.setRegUserSeq(refrigerator.getId());
        food.setModUserSeq(refrigerator.getId());
        food.setMissRegistered(missRegistered);

        return food;
    }
}
