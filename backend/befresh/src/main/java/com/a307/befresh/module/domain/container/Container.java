package com.a307.befresh.module.domain.container;

import com.a307.befresh.module.domain.Ftype.Ftype;
import com.a307.befresh.module.domain.food.Food;
import com.a307.befresh.module.domain.refresh.Refresh;
import com.a307.befresh.module.domain.refrigerator.Refrigerator;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
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

    public static Container createContainer(String name, String image, LocalDate expirationDate,
        Refresh refresh, Ftype ftype, Refrigerator refrigerator, boolean missRegistered,
        Double temperature,
        Double humidity, Double zCoordinate, Long qrId) {

        Container container = new Container();

        container.setName(name);
        container.setImage(image);
        container.setExpirationDate(expirationDate);
        container.setRefresh(refresh);
        container.setFtype(ftype);
        container.setRefrigerator(refrigerator);
        container.setMissRegistered(missRegistered);

        container.setTemperature(temperature);
        container.setHumidity(humidity);
        container.setZCoordinate(zCoordinate);
        container.setQrId(qrId);

        container.setRegUserSeq(refrigerator.getId());
        container.setModUserSeq(refrigerator.getId());

        return container;
    }
}
