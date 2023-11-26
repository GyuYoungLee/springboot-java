package com.example.bookintro.service;

import com.example.bookintro.dto.request.CommentReq;
import com.example.bookintro.entity.Article;
import com.example.bookintro.entity.Comment;
import com.example.bookintro.repository.ArticleRepository;
import com.example.bookintro.repository.CommentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class CommentServiceTest {
    @Autowired
    private CommentService commentService;
    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private CommentRepository commentRepository;

    @BeforeEach
    public void setUp() {
        commentRepository.deleteAll();
        articleRepository.deleteAll();
    }

    @Test
    void createComment() {
        // given
        Article article = articleRepository.save(Article.builder()
                .title("cat")
                .content("meow").build());

        // when
        CommentReq dto = new CommentReq("jay", "good");
        Comment created = commentService.createComment(dto.toEntity(article));

        // then
        assertEquals("jay", created.getNickname());
        assertEquals("good", created.getBody());
    }

    @Test
    void getCommentList() {
        // given
        Article article = articleRepository.save(Article.builder()
                .title("cat")
                .content("meow").build());
        Comment given = commentRepository.save(Comment.builder()
                .nickname("jay")
                .body("good")
                .article(article).build());

        // when
        List<Comment> commentList = commentService.getCommentList(given.getArticle().getId());

        // then
        assertEquals("jay", commentList.get(0).getNickname());
        assertEquals("good", commentList.get(0).getBody());
    }

    @Test
    void updateComment() {
        // given
        Article article = articleRepository.save(Article.builder()
                .title("cat")
                .content("meow").build());
        Comment given = commentRepository.save(Comment.builder()
                .nickname("jay")
                .body("good")
                .article(article).build());

        // when
        CommentReq dto = new CommentReq("jay", "bad");
        Comment updated = commentService.updateComment(given.getId(), dto.toEntity(null));

        // then
        assertEquals("jay", updated.getNickname());
        assertEquals("bad", updated.getBody());
    }

    @Test
    void updateComment_Exception() {
        // when
        CommentReq dto = new CommentReq("jay", "good");
        assertThrows(IllegalArgumentException.class, () -> {
            commentService.updateComment(-1L, dto.toEntity(null));
        });
    }

    @Test
    void deleteComment() {
        // given
        Article article = articleRepository.save(Article.builder()
                .title("cat")
                .content("meow").build());
        Comment given = commentRepository.save(Comment.builder()
                .nickname("jay")
                .body("good")
                .article(article).build());

        // when
        Comment deleted = commentService.deleteComment(given.getId());

        // then
        assertEquals("jay", deleted.getNickname());
        assertEquals("good", deleted.getBody());
    }

    @Test
    void deleteComment_Exception() {
        // when
        assertThrows(IllegalArgumentException.class, () -> {
            commentService.deleteComment(-1L);
        });
    }
}