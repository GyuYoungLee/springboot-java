package com.example.core07test.repository;

import com.example.core07test.entity.Article;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ArticleRepositoryTest {
    @Autowired
    private ArticleRepository articleRepository;

    @BeforeEach
    public void setUp() {
        articleRepository.deleteAll();
    }

    @Test
    void findByTitle() {
        // given
        articleRepository.save(Article.builder()
                .title("cat")
                .content("meow").build());

        // when
        List<Article> articleList = articleRepository.findByTitle("cat");

        // then
        assertEquals("cat", articleList.get(0).getTitle());
        assertEquals("meow", articleList.get(0).getContent());
    }
}
