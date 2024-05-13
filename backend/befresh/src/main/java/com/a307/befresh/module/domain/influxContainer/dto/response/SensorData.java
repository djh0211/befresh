package com.a307.befresh.module.domain.influxContainer.dto.response;

import java.time.Instant;
import java.time.LocalDateTime;
import lombok.Builder;

@Builder
public record SensorData(

    String type,
    Double value,

    Instant time

) {

}
