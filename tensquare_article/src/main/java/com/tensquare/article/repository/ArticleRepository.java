package com.tensquare.article.repository;

import com.tensquare.article.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ArticleRepository extends JpaRepository<Article, Long>, JpaSpecificationExecutor<Article> {

    @Modifying
    @Query(value = "UPDATE tb_article SET state =1 WHERE id=?", nativeQuery = true)
    void examine(Long id);

    @Modifying
    @Query(value = "UPDATE tb_article SET thumbup =thumbup+1 WHERE id=?", nativeQuery = true)
    void thumbup(Long id);
}
