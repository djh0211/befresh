package com.a307.befresh.module.domain.elastic.repository;

import com.a307.befresh.module.domain.elastic.ElasticDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ElasticRepository extends ElasticsearchRepository<ElasticDocument, String>, ElasticCustomRepository {

}
