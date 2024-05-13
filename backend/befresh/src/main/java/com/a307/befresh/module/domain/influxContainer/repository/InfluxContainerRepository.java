package com.a307.befresh.module.domain.influxContainer.repository;

import com.a307.befresh.module.domain.influxContainer.InfluxContainer;
import com.a307.befresh.module.domain.influxContainer.dto.response.SensorData;
import com.a307.befresh.module.domain.influxContainer.dto.response.SensorDataList;
import com.influxdb.query.FluxRecord;
import com.influxdb.query.FluxTable;
import java.util.List;

public interface InfluxContainerRepository {

    SensorDataList selectSensorData(String qrId);
}
