package com.a307.befresh.module.domain.elastic.controller;

import com.a307.befresh.global.api.response.BaseResponse;
import com.a307.befresh.global.exception.code.SuccessCode;
import com.a307.befresh.module.domain.elastic.ElasticDocument;
import com.a307.befresh.module.domain.elastic.service.ElasticService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/elastic")
@RequiredArgsConstructor
@CrossOrigin("*")
@Slf4j
public class ElasticController {

    private final ElasticService elasticService;
    @GetMapping("/befresh")
    public ResponseEntity<BaseResponse<List<ElasticDocument>>> registerFood(
            @RequestParam String name) {

        List<ElasticDocument> elasticDocuments = elasticService.searchBefreshByName(name);

        return BaseResponse.success(SuccessCode.SELECT_SUCCESS, elasticDocuments);
    }

}

