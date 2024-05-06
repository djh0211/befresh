package com.a307.befresh.module.domain.elastic.repository;

import com.a307.befresh.module.domain.elastic.ElasticDocument;

import java.util.List;

public interface ElasticCustomRepository {
    List<ElasticDocument> searchBefreshByName(String name);
}
