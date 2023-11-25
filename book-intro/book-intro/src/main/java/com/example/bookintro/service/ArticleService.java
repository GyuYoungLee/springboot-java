package com.example.bookintro.service;

import com.example.bookintro.entity.Article;
import com.example.bookintro.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;

    // 생성
    public Article createArticle(Article article) {
        return articleRepository.save(article);
    }

    // 조회
    public List<Article> getArticleList() {
        return articleRepository.findAll();
    }

    public Article getArticle(long id) {
        return articleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 글 없음"));
    }

    // 수정
    public Article updateArticle(long id, Article article) {
        Article target = articleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 글 없음"));

        target.setTitle(article.getTitle());
        target.setContent(article.getContent());
        return articleRepository.save(target);
    }

    // 삭제
    public Article deleteArticle(long id) {
        Article target = articleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 글 없음"));

        articleRepository.delete(target);
        return target;
    }
}
