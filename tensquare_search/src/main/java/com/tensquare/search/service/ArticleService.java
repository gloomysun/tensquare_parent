package com.tensquare.search.service;

import com.tensquare.search.entity.Article;
import com.tensquare.search.repository.ArticleRepository;
import com.tensquare.utils.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private IdWorker idWorker;
    public void add(Article article) {
        article.setId(idWorker.nextId());
        articleRepository.save(article);
    }

    public Page findByKeyword(String keyword, int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        return articleRepository.findByTitleOrContentLike(keyword, keyword, pageable);
    }
}
