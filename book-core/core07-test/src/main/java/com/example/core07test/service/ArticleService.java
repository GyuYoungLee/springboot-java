package com.example.core07test.service;

import com.example.core07test.entity.Article;
import com.example.core07test.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;

    public Article findArticle(long id) {
        return articleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 상품 없음"));
    }

    public Article saveArticle(Article article) {
        return articleRepository.save(article);
    }

    public Article deleteArticle(long id) {
        Article target = articleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 상품 없음"));

        articleRepository.delete(target);
        return target;
    }
}
