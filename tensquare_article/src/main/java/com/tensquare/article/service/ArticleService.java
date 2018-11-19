package com.tensquare.article.service;

import com.tensquare.article.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;

    @Transactional
    public void examine(Long id) {
        articleRepository.examine(id);
    }

    @Transactional
    public void thumbup(Long id) {
        articleRepository.thumbup(id);
    }
}
