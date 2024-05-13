package com.a307.befresh.module.domain.influxContainer;

import com.influxdb.annotations.Column;
import com.influxdb.annotations.Measurement;
import java.time.LocalDateTime;

@Measurement(name = "container")
public class InfluxContainer {

    @Column(tag = true, name = "qr_id")
    String qrId;

    @Column(name="time", timestamp = true)
    LocalDateTime time;

    @Column(name="ph_values")
    double phValues;

    @Column
    double temperature;

    @Column
    double humidity;

    @Column
    double gas;

    @Column
    double nh3;

}
