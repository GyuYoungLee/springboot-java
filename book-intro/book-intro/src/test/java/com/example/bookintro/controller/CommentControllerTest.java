package com.example.bookintro.controller;

import com.example.bookintro.dto.request.CommentReq;
import com.example.bookintro.entity.Article;
import com.example.bookintro.entity.Comment;
import com.example.bookintro.repository.ArticleRepository;
import com.example.bookintro.repository.CommentRepository;
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
class CommentControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
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
    void createComment() throws Exception {
        // given
        Article article = articleRepository.save(Article.builder()
                .title("cat")
                .content("meow").build());

        // when
        CommentReq dto = new CommentReq("jay", "good");
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders
                .post("/api/articles/" + article.getId() + "/comments")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)));

        // then
        result.andExpect(status().isOk())
                .andExpect(jsonPath("nickname").value("jay"))
                .andExpect(jsonPath("body").value("good"));
    }

    @Test
    void getCommentList() throws Exception {
        // given
        Article article = articleRepository.save(Article.builder()
                .title("cat")
                .content("meow").build());
        Comment given = commentRepository.save(Comment.builder()
                .nickname("jay")
                .body("good")
                .article(article).build());

        // when
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders
                .get("/api/articles/" + given.getArticle().getId() + "/comments"));

        // then
        result.andExpect(status().isOk())
                .andExpect(jsonPath("[0].nickname").value("jay"))
                .andExpect(jsonPath("[0].body").value("good"));
    }

    @Test
    void updateComment() throws Exception {
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
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders
                .patch("/api/comments/" + given.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)));

        // then
        result.andExpect(status().isOk())
                .andExpect(jsonPath("nickname").value("jay"))
                .andExpect(jsonPath("body").value("bad"));
    }

    @Test
    void deleteComments() throws Exception {
        // given
        Article article = articleRepository.save(Article.builder()
                .title("cat")
                .content("meow").build());
        Comment given = commentRepository.save(Comment.builder()
                .nickname("jay")
                .body("good")
                .article(article).build());

        // when
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders
                .delete("/api/comments/" + given.getId()));

        // then
        result.andExpect(status().isOk());
        assertTrue(commentRepository.findById(given.getId()).isEmpty());
    }
}
