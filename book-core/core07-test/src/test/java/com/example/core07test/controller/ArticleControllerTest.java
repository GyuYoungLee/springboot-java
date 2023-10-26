package com.example.core07test.controller;

import com.example.core07test.dto.AddArticleRequest;
import com.example.core07test.entity.Article;
import com.example.core07test.repository.ArticleRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ArticleControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private ArticleRepository articleRepository;

    @BeforeEach
    public void setUp() {
        articleRepository.deleteAll();
    }

    @Test
    void getArticle() throws Exception {
        // given
        Article article = articleRepository.save(Article.builder()
                .title("cat")
                .content("meow").build());

        // when
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders
                .get("/api/v1/articles/" + article.getId()));

        // then
        result.andExpect(status().isOk())
                .andExpect(jsonPath("title").value("cat"))
                .andExpect(jsonPath("content").value("meow"));
    }

    @Test
    void createArticle() throws Exception {
        // when
        AddArticleRequest dto = new AddArticleRequest("cat", "meow");

        ResultActions result = mockMvc.perform(MockMvcRequestBuilders
                .post("/api/v1/articles")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)));

        // then
        result.andExpect(status().isOk())
                .andExpect(jsonPath("title").value("cat"))
                .andExpect(jsonPath("content").value("meow"));
    }

    @Test
    void deleteArticle() throws Exception {
        // given
        Article article = articleRepository.save(Article.builder()
                .title("cat")
                .content("meow").build());

        // when
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders
                .delete("/api/v1/articles/" + article.getId()));

        // then
        result.andExpect(status().isOk());
        assertTrue(articleRepository.findById(article.getId()).isEmpty());
    }
}
