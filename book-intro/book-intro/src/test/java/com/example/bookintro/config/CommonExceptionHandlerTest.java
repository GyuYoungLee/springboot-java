package com.example.bookintro.config;

import com.example.bookintro.entity.Article;
import com.example.bookintro.repository.ArticleRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class CommonExceptionHandlerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void handleException() throws Exception {
        // when
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders
                .get("/api/articles/" + -1L));

        // then
        result.andExpect(status().isBadRequest())
                .andExpect(content().string("해당 글 없음"));
    }
}
