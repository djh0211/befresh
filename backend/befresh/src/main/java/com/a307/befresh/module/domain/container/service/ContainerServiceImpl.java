package com.a307.befresh.module.domain.container.service;

import com.a307.befresh.module.domain.container.Container;
import com.a307.befresh.module.domain.container.dto.request.ContainerUpdateSensorListReq;
import com.a307.befresh.module.domain.container.repository.ContainerRepository;
import com.a307.befresh.module.domain.influxContainer.dto.response.ContainerSensor;
import com.a307.befresh.module.domain.influxContainer.dto.response.SensorDataList;
import com.a307.befresh.module.domain.influxContainer.repository.InfluxContainerRepository;
import com.a307.befresh.module.domain.refresh.Refresh;
import com.a307.befresh.module.domain.refresh.repository.RefreshRepository;
import jakarta.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class ContainerServiceImpl implements ContainerService {

    private final ContainerRepository containerRepository;
    private final RefreshRepository refreshRepository;
    private final InfluxContainerRepository influxContainerRepository;

    @Transactional
    public void updateContainerSensor(ContainerUpdateSensorListReq containerUpdateSensorListReq) {

        containerUpdateSensorListReq.containerUpdateSensorReqList().stream()
            .forEach(containerUpdateSensorReq -> {
                Container container = containerRepository.findByQrId(
                    containerUpdateSensorReq.qrId());

                // 신선도 처리 로직
                Refresh refresh = calculateFreshness(
                    containerUpdateSensorReq.nh3(),
                    containerUpdateSensorReq.gas1(),
                    containerUpdateSensorReq.gas2()
                );

                container.setTemperature(containerUpdateSensorReq.temperature());
                container.setHumidity(containerUpdateSensorReq.humidity());
                container.setRefresh(refresh);
            });
    }

    @Override
    public List<ContainerSensor> getContainerSensor(Long refrigeratorId) {

        List<Container> containerList = containerRepository.findByRefrigerator_Id(refrigeratorId);

        List<ContainerSensor> containerSensorList = new ArrayList<>();

        for (Container container : containerList) {

            int elapsedTime = calculateElapsedTime(container.getRegDttm());

            SensorDataList sensorDataList = influxContainerRepository.selectSensorData(
                container.getRegDttm(), container.getQrId());

            ContainerSensor containerSensor = ContainerSensor.builder()
                .qrId(container.getQrId())
                .name(container.getName())
                .regDttm(container.getRegDttm())
                .elapsedTime(elapsedTime)
                .expirationDate(container.getExpirationDate())
                .refresh(container.getRefresh().getName())
                .sensorDataList(sensorDataList)
                .build();

            containerSensorList.add(containerSensor);

        }

        return containerSensorList;
    }

    private int calculateElapsedTime(LocalDateTime registrationDateTime) {
        return Period.between(registrationDateTime.toLocalDate(), LocalDate.now()).getDays();
    }

    private Refresh calculateFreshness(double nh3, double gas1, double gas2) {
        // TODO : R 통계 분석을 이용한 음식의 신선도 계산

        return refreshRepository.findById(1L).orElseThrow();
    }
}