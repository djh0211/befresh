package com.a307.befresh.module.domain.container.service;

import com.a307.befresh.module.domain.container.dto.request.ContainerUpdateSensorListReq;
import com.a307.befresh.module.domain.container.dto.request.ContainerUpdateSensorReq;

public interface ContainerService {
    void updateContainerSensor(ContainerUpdateSensorListReq containerUpdateSensorListReq);
}
