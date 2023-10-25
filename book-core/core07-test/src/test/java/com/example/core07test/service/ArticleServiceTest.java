package com.example.core07test.service;

import com.example.core07test.entity.Article;
import com.example.core07test.repository.ArticleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
    void findArticle() {
        // given
        Article article = articleRepository.save(Article.builder()
                .title("cat")
                .content("meow").build());

        // when
        Article found = articleService.findArticle(article.getId());

        // then
        assertEquals("cat", found.getTitle());
        assertEquals("meow", found.getContent());
    }

    @Test
    void findArticle_exception() {
        // when
        assertThrows(IllegalArgumentException.class, () -> {
            articleService.findArticle(-1L);
        });
    }

    @Test
    void saveArticle() {
        // when
        Article saved = articleService.saveArticle(Article.builder()
                .title("cat")
                .content("meow").build());

        // then
        assertEquals("cat", saved.getTitle());
        assertEquals("meow", saved.getContent());
    }

    @Test
    void deleteArticle() {
        // given
        Article article = articleRepository.save(Article.builder()
                .title("cat")
                .content("meow").build());

        // when
        Article deleted = articleService.deleteArticle(article.getId());

        // then
        assertEquals("cat", article.getTitle());
        assertEquals("meow", article.getContent());
        assertTrue(articleRepository.findById(article.getId()).isEmpty());
    }

    @Test
    void deleteArticle_exception() {
        // when
        assertThrows(IllegalArgumentException.class, () -> {
            articleService.deleteArticle(-1L);
        });
    }
}
