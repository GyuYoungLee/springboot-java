package com.example.bookintro.controller;

import com.example.bookintro.dto.request.ArticleReq;
import com.example.bookintro.entity.Article;
import com.example.bookintro.repository.ArticleRepository;
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

import static org.junit.jupiter.api.Assertions.*;
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
    void createArticle()  throws Exception {
        // when
        ArticleReq dto = new ArticleReq("cat", "meow");
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders
                .post("/api/articles")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)));

        // then
        result.andExpect(status().isOk())
                .andExpect(jsonPath("title").value("cat"))
                .andExpect(jsonPath("content").value("meow"));
    }

    @Test
    void getArticleList()  throws Exception {
        // given
        Article given = articleRepository.save(Article.builder()
                .title("cat")
                .content("meow").build());

        // when
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders
                .get("/api/articles"));

        // then
        result.andExpect(status().isOk())
                .andExpect(jsonPath("[0].title").value("cat"))
                .andExpect(jsonPath("[0].content").value("meow"));
    }

    @Test
    void getArticle()  throws Exception {
        // given
        Article given = articleRepository.save(Article.builder()
                .title("cat")
                .content("meow").build());

        // when
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders
                .get("/api/articles/" + given.getId()));

        // then
        result.andExpect(status().isOk())
                .andExpect(jsonPath("title").value("cat"))
                .andExpect(jsonPath("content").value("meow"));
    }

    @Test
    void updateArticle()  throws Exception {
        // given
        Article given = articleRepository.save(Article.builder()
                .title("cat")
                .content("meow").build());

        // when
        ArticleReq dto = new ArticleReq("cat", "bark");
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders
                .patch("/api/articles/" + given.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)));

        // then
        result.andExpect(status().isOk())
                .andExpect(jsonPath("title").value("cat"))
                .andExpect(jsonPath("content").value("bark"));
    }

    @Test
    void deleteArticle() throws Exception {
        // given
        Article given = articleRepository.save(Article.builder()
                .title("cat")
                .content("meow").build());

        // when
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders
                .delete("/api/articles/" + given.getId()));

        // then
        result.andExpect(status().isOk());
        assertTrue(articleRepository.findById(given.getId()).isEmpty());
    }
}