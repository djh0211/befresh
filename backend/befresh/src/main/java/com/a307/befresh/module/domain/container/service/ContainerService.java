package com.a307.befresh.module.domain.container.service;

import com.a307.befresh.module.domain.container.dto.request.ContainerUpdateSensorListReq;
import com.a307.befresh.module.domain.container.dto.request.ContainerUpdateSensorReq;
import com.a307.befresh.module.domain.influxContainer.dto.response.ContainerSensor;
import com.influxdb.query.FluxRecord;
import java.util.List;

public interface ContainerService {
    void updateContainerSensor(ContainerUpdateSensorListReq containerUpdateSensorListReq);
    List<ContainerSensor> getContainerSensor(Long refrigeratorId);
}
