package com.example.bookintro.controller;

import com.example.bookintro.dto.request.ArticleReq;
import com.example.bookintro.dto.response.ArticleRes;
import com.example.bookintro.entity.Article;
import com.example.bookintro.service.ArticleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api")
@RestController
@RequiredArgsConstructor
@Slf4j
public class ArticleController {
    private final ArticleService articleService;

    // 생성
    @PostMapping("/articles")
    public ArticleRes createArticle(@RequestBody ArticleReq dto) {
        Article created = articleService.createArticle(dto.toEntity());
        log.info("created = " + created);
        return new ArticleRes(created);
    }

    // 조회
    @GetMapping("/articles")
    public List<ArticleRes> getArticleList() {
        List<Article> articleList = articleService.getArticleList();
        return articleList.stream().map(ArticleRes::new).toList();
    }

    @GetMapping("/articles/{id}")
    public ArticleRes getArticle(@PathVariable long id) {
        Article article = articleService.getArticle(id);
        log.info("article = " + article);
        return new ArticleRes(article);
    }

    // 수정
    @PatchMapping("/articles/{id}")
    public ArticleRes updateArticle(@PathVariable long id, @RequestBody ArticleReq dto) {
        Article updated = articleService.updateArticle(id, dto.toEntity());
        log.info("updated = " + updated);
        return new ArticleRes(updated);
    }

    // 삭제
    @DeleteMapping("/articles/{id}")
    public void deleteArticle(@PathVariable long id) {
        Article deleted = articleService.deleteArticle(id);
        log.info("deleted = " + deleted);
    }
}
