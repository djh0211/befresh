package com.a307.befresh.module.domain.container.dto.request;

public record ContainerUpdateSensorReq(
        String qrId,
        double nh3,
        double gas1,
        double gas2,
        double temperature,
        double humidity
) {
}
