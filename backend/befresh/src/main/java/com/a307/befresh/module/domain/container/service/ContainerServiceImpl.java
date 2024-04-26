package com.a307.befresh.module.domain.container.service;

import com.a307.befresh.module.domain.container.Container;
import com.a307.befresh.module.domain.container.dto.request.ContainerUpdateSensorListReq;
import com.a307.befresh.module.domain.container.repository.ContainerRepository;
import com.a307.befresh.module.domain.container.dto.request.ContainerUpdateSensorReq;
import com.a307.befresh.module.domain.refresh.Refresh;
import com.a307.befresh.module.domain.refresh.repository.RefreshRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class ContainerServiceImpl implements ContainerService {
    private final ContainerRepository containerRepository;
    private final RefreshRepository refreshRepository;

    @Transactional
    public void updateContainerSensor(ContainerUpdateSensorListReq containerUpdateSensorListReq) {

        containerUpdateSensorListReq.containerUpdateSensorReqList().stream()
                .forEach(containerUpdateSensorReq -> {
                    Container container = containerRepository.findByQrId(containerUpdateSensorReq.qrId());

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

    private Refresh calculateFreshness(double nh3, double gas1, double gas2) {
        // TODO : R 통계 분석을 이용한 음식의 신선도 계산

        return refreshRepository.findById(1L).orElseThrow();
    }
}