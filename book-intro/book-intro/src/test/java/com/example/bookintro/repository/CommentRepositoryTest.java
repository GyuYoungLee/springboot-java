package com.example.bookintro.repository;

import com.example.bookintro.entity.Article;
import com.example.bookintro.entity.Comment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CommentRepositoryTest {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ArticleRepository articleRepository;

    @BeforeEach
    public void setUp() {
        commentRepository.deleteAll();
        articleRepository.deleteAll();
    }

    @Test
    void findByArticleId() {
        // given
        Article article = articleRepository.save(Article.builder()
                .title("cat")
                .content("meow").build());
        Comment given = commentRepository.save(Comment.builder()
                .nickname("jay")
                .body("good")
                .article(article).build());

        // when
        List<Comment> commentList = commentRepository.findByArticleId(given.getArticle().getId());

        // then
        assertEquals("jay", commentList.get(0).getNickname());
        assertEquals("good", commentList.get(0).getBody());
    }
}
