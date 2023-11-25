package com.example.bookintro.service;

import com.example.bookintro.dto.request.ArticleReq;
import com.example.bookintro.entity.Article;
import com.example.bookintro.repository.ArticleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ArticleServiceTest {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private ArticleRepository articleRepository;

    @BeforeEach
    public void setUp() {
        articleRepository.deleteAll();
    }

    @Test
    void createArticle() {
        // when
        Article created = articleService.createArticle(Article.builder()
                .title("cat")
                .content("meow").build());

        // then
        assertEquals("cat", created.getTitle());
        assertEquals("meow", created.getContent());
    }

    @Test
    void getArticleList() {
        // given
        articleRepository.save(Article.builder()
                .title("cat")
                .content("meow").build());

        // when
        List<Article> articleList = articleService.getArticleList();

        // then
        assertEquals("cat", articleList.get(0).getTitle());
        assertEquals("meow", articleList.get(0).getContent());
    }

    @Test
    void getArticle() {
        // given
        Article given = articleRepository.save(Article.builder()
                .title("cat")
                .content("meow").build());

        // when
        Article article = articleService.getArticle(given.getId());

        // then
        assertEquals("cat", article.getTitle());
        assertEquals("meow", article.getContent());
    }

    @Test
    void getArticle_Exception() {
        // when
        assertThrows(IllegalArgumentException.class, () -> {
            articleService.getArticle(-1L);
        });
    }

    @Test
    void updateArticle() {
        // given
        Article given = articleRepository.save(Article.builder()
                .title("cat")
                .content("meow").build());

        // when
        ArticleReq dto = new ArticleReq("cat", "bark");
        Article updated = articleService.updateArticle(given.getId(), dto.toEntity());

        // then
        assertEquals("cat", updated.getTitle());
        assertEquals("bark", updated.getContent());
    }

    @Test
    void updateArticle_Exception() {
        // when
        ArticleReq dto = new ArticleReq("cat", "meow");
        assertThrows(IllegalArgumentException.class, () -> {
            articleService.updateArticle(-1L, dto.toEntity());
        });
    }

    @Test
    void deleteArticle() {
        // given
        Article given = articleRepository.save(Article.builder()
                .title("cat")
                .content("meow").build());

        // when
        Article deleted = articleService.deleteArticle(given.getId());

        // then
        assertEquals("cat", deleted.getTitle());
        assertEquals("meow", deleted.getContent());
    }

    @Test
    void deleteArticle_Exception() {
        // when
        assertThrows(IllegalArgumentException.class, () -> {
            articleService.deleteArticle(-1L);
        });
    }
}
