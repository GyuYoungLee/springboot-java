package com.example.bookintro.controller;

import com.example.bookintro.dto.request.CommentReq;
import com.example.bookintro.dto.response.CommentRes;
import com.example.bookintro.entity.Article;
import com.example.bookintro.entity.Comment;
import com.example.bookintro.service.ArticleService;
import com.example.bookintro.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api")
@RestController
@RequiredArgsConstructor
@Slf4j
public class CommentController {
    private final CommentService commentService;
    private final ArticleService articleService;

    // 생성
    @PostMapping("/articles/{articleId}/comments")
    public CommentRes createComment(@PathVariable long articleId, @RequestBody CommentReq dto) {
        Article article = articleService.getArticle(articleId);
        Comment created = commentService.createComment(dto.toEntity(article));
        log.info("created = " + created);
        return new CommentRes(created);
    }

    // 조회
    @GetMapping("/articles/{articleId}/comments")
    public List<CommentRes> getCommentList(@PathVariable long articleId) {
        List<Comment> commentList = commentService.getCommentList(articleId);
        return commentList.stream().map(CommentRes::new).toList();
    }

    // 수정
    @PatchMapping("/comments/{id}")
    public CommentRes updateComment(@PathVariable long id, @RequestBody CommentReq dto) {
        Comment updated = commentService.updateComment(id, dto.toEntity(null));
        log.info("updated = " + updated);
        return new CommentRes(updated);
    }

    // 삭제
    @DeleteMapping("/comments/{id}")
    public void deleteComments(@PathVariable long id) {
        Comment deleted = commentService.deleteComment(id);
        log.info("deleted = " + deleted);
    }
}
