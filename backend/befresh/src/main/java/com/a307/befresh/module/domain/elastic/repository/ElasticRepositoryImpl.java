package com.a307.befresh.module.domain.elastic.repository;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.query_dsl.MatchQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import com.a307.befresh.module.domain.elastic.ElasticDocument;
import java.util.ArrayList;
import lombok.RequiredArgsConstructor;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
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


        Query stand = MatchQuery.of(t -> t.field("name").query(name).boost(2f))._toQuery();
        Query nori = MatchQuery.of(t -> t.field("name.nori").query(name))._toQuery();
        Query ngram = MatchQuery.of(t -> t.field("name.ngram").query(name))._toQuery();

        NativeQuery query = NativeQuery.builder()
            .withQuery(Query.of(qb ->
                qb.bool(b -> b
                    .should(stand)
                    .should(nori)
                    .should(ngram)
                )))
            .build();


        SearchHits<ElasticDocument> searchHits = operations.search(query, ElasticDocument.class);
        List<ElasticDocument> resultList = new ArrayList<>();

        for(SearchHit<ElasticDocument> hit: searchHits) {
            if(hit.getScore() <= 4) continue;

            ElasticDocument document = hit.getContent();
            document.setScore(hit.getScore());
            resultList.add(document);
        }

        return resultList;
    }


}
