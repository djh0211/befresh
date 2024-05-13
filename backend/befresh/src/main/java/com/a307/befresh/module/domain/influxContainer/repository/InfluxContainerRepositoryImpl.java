package com.a307.befresh.module.domain.influxContainer.repository;

import com.a307.befresh.module.domain.influxContainer.InfluxContainer;
import com.a307.befresh.module.domain.influxContainer.dto.response.SensorData;
import com.a307.befresh.module.domain.influxContainer.dto.response.SensorDataList;
import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.InfluxDBClientFactory;
import com.influxdb.client.QueryApi;
import com.influxdb.query.FluxRecord;
import com.influxdb.query.FluxTable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class InfluxContainerRepositoryImpl implements InfluxContainerRepository{

    @Value("${spring.influxdb2.url}")
    private String url;
    @Value("${spring.influxdb2.token}")
    private char[] token;
    @Value("${spring.influxdb2.org}")
    private String org;
    @Value("${spring.influxdb2.bucket}")
    private String bucket;


    @Override
    public SensorDataList selectSensorData(String qrId) {

        InfluxDBClient influxDBClient = InfluxDBClientFactory.create(url, token, org, bucket);

        String flux = String.format("from(bucket: \"befresh\")"
            + "|> range(start: 0, stop: now())"
            + "|> filter(fn: (r) => r[\"_field\"] == \"humidity\" or r[\"_field\"] == \"temperature\" or r[\"_field\"] == \"nh3\")"
            + "|> filter(fn: (r) => r[\"qr_id\"] == \"%s\")"
            + "|> aggregateWindow(every: 10m, fn: mean, createEmpty: false)"
            + "|> yield(name: \"sensor_data\")", qrId);

        QueryApi queryApi = influxDBClient.getQueryApi();

        List<FluxTable> tables = queryApi.query(flux);

        List<SensorData> temperature = new ArrayList<>();
        List<SensorData> humidity = new ArrayList<>();
        List<SensorData> nh3 = new ArrayList<>();

        for(FluxTable fluxTable: tables) {
            List<FluxRecord> records = fluxTable.getRecords();
            for(FluxRecord fluxRecord : records) {

                SensorData sensorData = SensorData.builder()
                    .type(fluxRecord.getField())
                    .time(fluxRecord.getTime())
                    .value((Double) fluxRecord.getValue())
                    .build();

                if(sensorData.type().equals("temperature")) {
                    temperature.add(sensorData);
                } else if(sensorData.type().equals("humidity")){
                    humidity.add(sensorData);
                } else if(sensorData.type().equals("nh3")) {
                    nh3.add(sensorData);
                }
            }
        }

        return SensorDataList.builder()
            .temperature(temperature)
            .humidity(humidity)
            .nh3(nh3)
            .build();
    }
}
