package com.example.core07test.common;

import com.example.core07test.controller.ProductController;
import com.example.core07test.service.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
class CommonExceptionHandlerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ProductService productService;

    @Test
    void handleException() throws Exception {
        // given
        BDDMockito
                .given(productService.findProduct(-1L))
                .willThrow(new IllegalArgumentException("해당 상품 없음"));

        // when
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders
                .get("/api/v1/products/" + -1L));

        // then
        result.andExpect(status().isBadRequest())
                .andExpect(content().string("해당 상품 없음"));
        BDDMockito.then(productService).should().findProduct(-1L);
    }
}
