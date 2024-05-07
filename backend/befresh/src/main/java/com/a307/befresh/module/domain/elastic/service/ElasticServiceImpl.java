package com.a307.befresh.module.domain.elastic.service;

import com.a307.befresh.module.domain.elastic.ElasticDocument;
import com.a307.befresh.module.domain.elastic.repository.ElasticRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ElasticServiceImpl implements ElasticService {

    private final ElasticRepository elasticRepository;

    @Override
    public List<ElasticDocument> searchBefreshByName(String name) {
        return elasticRepository.searchBefreshByName(name);
    }
}
