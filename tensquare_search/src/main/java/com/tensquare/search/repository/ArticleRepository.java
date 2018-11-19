package com.tensquare.search.repository;

import com.tensquare.search.entity.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ArticleRepository extends ElasticsearchRepository<Article, Long> {

    Page<Article> findByTitleOrContentLike(String title,String content, Pageable pageable);
}
