package com.a307.befresh.module.domain.container.controller;

import com.a307.befresh.global.api.response.BaseResponse;
import com.a307.befresh.global.exception.code.SuccessCode;
import com.a307.befresh.global.security.domain.UserDetailsImpl;
import com.a307.befresh.module.domain.container.dto.request.ContainerUpdateSensorListReq;
import com.a307.befresh.module.domain.container.dto.request.ContainerUpdateSensorReq;
import com.a307.befresh.module.domain.container.service.ContainerService;
import com.a307.befresh.module.domain.influxContainer.dto.response.ContainerSensor;
import com.fasterxml.jackson.databind.ser.Serializers.Base;
import com.influxdb.query.FluxRecord;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/containers")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ContainerController {
    private final ContainerService containerService;

    @PostMapping("/v1")
    public ResponseEntity<BaseResponse<Integer>> updateContainerSensor(
        @RequestBody ContainerUpdateSensorListReq containerUpdateSensorListReq) throws IOException {

        containerService.updateContainerSensor(containerUpdateSensorListReq);

        return BaseResponse.success(SuccessCode.UPDATE_SUCCESS, 0);
    }

    @GetMapping("/sensor")
    public ResponseEntity<BaseResponse<List<ContainerSensor>>> getContainerSensor(@RequestParam(required = false) Long foodId, @AuthenticationPrincipal UserDetailsImpl userDetails){

        List<ContainerSensor> containerSensorList = containerService.getContainerSensor(foodId, userDetails.getRefrigeratorId());

        return BaseResponse.success(SuccessCode.SELECT_SUCCESS, containerSensorList);
    }
}