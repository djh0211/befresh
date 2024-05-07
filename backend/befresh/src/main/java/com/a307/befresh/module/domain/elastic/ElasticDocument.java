package com.a307.befresh.module.domain.elastic;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "befresh")
public class ElasticDocument {

    @Id
    private String id;

    @Field(type= FieldType.Keyword, name = "category")
    private String category;

    @Field(type = FieldType.Text, name = "name")
    private String name;

    @Field(type=FieldType.Integer, name ="expiration_date")
    private int expiration_date;

    private double score;
}
