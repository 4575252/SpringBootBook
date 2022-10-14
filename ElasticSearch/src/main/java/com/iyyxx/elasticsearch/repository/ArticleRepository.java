package com.iyyxx.elasticsearch.repository;

import com.iyyxx.elasticsearch.entity.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @className: ArticleRepository
 * @description: TODO 类描述
 * @author: eric 4575252@gmail.com
 * @date: 2022/10/13/0013 15:59:35
 **/
public interface ArticleRepository extends ElasticsearchRepository<Article, Long> {

    Page<Article> findByTitleLike(String title, Pageable page);
}
