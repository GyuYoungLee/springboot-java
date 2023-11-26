package com.example.bookintro.controller;

import com.example.bookintro.entity.Article;
import com.example.bookintro.entity.Comment;
import com.example.bookintro.service.ArticleService;
import com.example.bookintro.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ArticleViewController {
    private final ArticleService articleService;
    private final CommentService commentService;

    // 목록 화면
    @GetMapping("/articles")
    public String index(Model model) {
        List<Article> articleList = articleService.getArticleList();
        model.addAttribute("articleList", articleList);
        return "articles/index";
    }

    // 상세 화면
    @GetMapping("/articles/{id}")
    public String detail(@PathVariable long id, Model model) {
        Article article = articleService.getArticle(id);
        List<Comment> commentList = commentService.getCommentList(id);
        model.addAttribute("article", article);
        model.addAttribute("commentList", commentList);
        return "articles/detail";
    }

    // 새글 폼
    @GetMapping("/articles/new")
    public String newForm() {
        return "articles/new";
    }

    // 수정 폼
    @GetMapping("/articles/{id}/edit")
    public String editForm(@PathVariable long id, Model model) {
        Article article = articleService.getArticle(id);
        model.addAttribute("article", article);
        return "articles/edit";
    }
}
