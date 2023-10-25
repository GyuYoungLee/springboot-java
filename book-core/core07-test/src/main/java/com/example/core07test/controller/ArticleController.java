package com.example.core07test.controller;

import com.example.core07test.dto.AddArticleRequest;
import com.example.core07test.dto.ArticleResponse;
import com.example.core07test.entity.Article;
import com.example.core07test.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1")
@RestController
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService articleService;

    @GetMapping("/articles/{id}")
    public ArticleResponse getArticle(@PathVariable long id) {
        Article found = articleService.findArticle(id);
        return new ArticleResponse(found);
    }

    @PostMapping("/articles")
    public ArticleResponse createArticle(@RequestBody AddArticleRequest dto) {
        Article saved = articleService.saveArticle(dto.toEntity());
        return new ArticleResponse(saved);
    }

    @DeleteMapping("/articles/{id}")
    public void deleteArticle(@PathVariable long id) {
        Article deleted = articleService.deleteArticle(id);
    }
}
