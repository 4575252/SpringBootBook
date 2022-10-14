package com.iyyxx.elasticsearch.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

/**
 * @className: Article
 * @description: TODO 类描述
 * @author: eric 4575252@gmail.com
 * @date: 2022/10/13/0013 15:57:28
 **/
@Data
@Document(indexName = "article", createIndex = false)
public class Article {
    @Id
    private Long id;

    @Field(store = true, searchAnalyzer = "ik_smart", analyzer = "ik_smart")
    private String author;

    @Field(store = true, searchAnalyzer = "ik_smart", analyzer = "ik_smart")
    private String title;

    @Field(store = true, searchAnalyzer = "ik_max_word", analyzer = "ik_max_word")
    private String content;
}
