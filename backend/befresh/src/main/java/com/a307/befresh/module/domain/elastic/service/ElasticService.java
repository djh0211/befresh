package com.a307.befresh.module.domain.elastic.service;

import com.a307.befresh.module.domain.elastic.ElasticDocument;

import java.util.List;

public interface ElasticService {

    List<ElasticDocument> searchBefreshByName(String name);
}
