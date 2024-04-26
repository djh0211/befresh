package com.a307.befresh.module.domain.container.dto.request;

import java.util.List;

public record ContainerUpdateSensorListReq(
        List<ContainerUpdateSensorReq> containerUpdateSensorReqList
) {
}
