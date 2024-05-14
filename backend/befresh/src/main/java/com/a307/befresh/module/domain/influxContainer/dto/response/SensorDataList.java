package com.a307.befresh.module.domain.influxContainer.dto.response;

import java.util.List;
import lombok.Builder;

@Builder
public record SensorDataList(
    List<SensorData> temperature,
    List<SensorData> humidity,
    List<SensorData> nh3
) {

}
