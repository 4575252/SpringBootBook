package com.iyyxx.elasticsearch;

import com.iyyxx.elasticsearch.entity.Article;
import com.iyyxx.elasticsearch.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@SpringBootTest
class ElasticSearchApplicationTests {

    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    @BeforeEach
    void setUp() {
        //如果索引存在则删除！
        if(elasticsearchRestTemplate.indexOps(IndexCoordinates.of("article")).exists()){
            elasticsearchRestTemplate.indexOps(IndexCoordinates.of("article")).delete();
        }
    }

    @AfterEach
    void tearDown() {
        //如果索引存在则删除！
        if(elasticsearchRestTemplate.indexOps(IndexCoordinates.of("article")).exists()){
            elasticsearchRestTemplate.indexOps(IndexCoordinates.of("article")).delete();
        }
    }

    @Autowired
    ArticleRepository articleRepository;

    @Test
    void testElasticSearchCRUD(){
        //添加一行数据
        Article article = new Article();
        article.setId(2l);
        article.setAuthor("小水");
        article.setTitle("spring boot 集成 elasticsearch");
        article.setContent("Elasticsearch 在速度和可扩展性方面都表现出色，而且还能够索引多种类型的内容，这意味着其可用于多种用例");
        elasticsearchRestTemplate.save(article);

        // 测试新增功能，判断索引创建成功
        Assertions.assertTrue(elasticsearchRestTemplate.indexOps(IndexCoordinates.of("article")).exists());

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        // 判断count为1
        Long count = elasticsearchRestTemplate.count(new NativeSearchQueryBuilder().build(),Article.class);
        log.info("article count is {}", count);
        Assertions.assertEquals(count, 1);


        // 测试查询功能，判断作者是否一致
        Assertions.assertEquals(elasticsearchRestTemplate.get("2", Article.class).getAuthor(),"小水");

        // 测试修改功能，判断修改后作者是否一致
        article.setAuthor("王二小");
        elasticsearchRestTemplate.save(article);
        Assertions.assertEquals(elasticsearchRestTemplate.get("2", Article.class).getAuthor(),"王二小");

        //测试删除功能,判断索引是否已清空
        elasticsearchRestTemplate.delete("2", Article.class);
        Assertions.assertNull(elasticsearchRestTemplate.get("2", Article.class));
    }
}
