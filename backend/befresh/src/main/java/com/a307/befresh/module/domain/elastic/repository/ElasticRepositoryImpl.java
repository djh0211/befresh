package com.a307.befresh.module.domain.elastic.repository;

import co.elastic.clients.elasticsearch._types.query_dsl.MatchQuery;

import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import com.a307.befresh.module.domain.elastic.ElasticDocument;
import lombok.RequiredArgsConstructor;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Repository
public class ElasticRepositoryImpl implements ElasticCustomRepository {

    private final ElasticsearchOperations operations;

    @Override
    public List<ElasticDocument> searchBefreshByName(String name) {


        Query stand = MatchQuery.of(t -> t.field("name").query(name))._toQuery();
        Query nori = MatchQuery.of(t -> t.field("name.nori").query(name))._toQuery();
        Query ngram = MatchQuery.of(t -> t.field("name.ngram").query(name))._toQuery();

        Query query = new Query.Builder()
                .bool(b -> b
                        .should(stand).minimumShouldMatch("1")
                        .should(nori).minimumShouldMatch("1")
                        .should(ngram).minimumShouldMatch("1")
                )
                .build();

        SearchHits<ElasticDocument> searchHits = operations.search((org.springframework.data.elasticsearch.core.query.Query) query, ElasticDocument.class);

        return searchHits.stream().map(SearchHit::getContent).collect(Collectors.toList());
    }


}
