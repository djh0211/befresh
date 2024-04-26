package com.a307.befresh.module.domain.container.controller;

import com.a307.befresh.global.api.response.BaseResponse;
import com.a307.befresh.global.exception.code.SuccessCode;
import com.a307.befresh.module.domain.container.dto.request.ContainerUpdateSensorListReq;
import com.a307.befresh.module.domain.container.dto.request.ContainerUpdateSensorReq;
import com.a307.befresh.module.domain.container.service.ContainerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
}