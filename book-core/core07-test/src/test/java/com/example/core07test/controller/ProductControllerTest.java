package com.example.core07test.controller;

import com.example.core07test.dto.AddProductRequest;
import com.example.core07test.entity.Product;
import com.example.core07test.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
class ProductControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private ProductService productService;

    @Test
    void getProduct() throws Exception {
        // given
        BDDMockito
                .given(productService.findProduct(1L))
                .willReturn(Product.builder()
                        .id(1L)
                        .name("pen")
                        .price(1000).build());

        // when
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders
                .get("/api/v1/products/" + 1L));

        // then
        result.andExpect(status().isOk())
                .andExpect(jsonPath("name").value("pen"))
                .andExpect(jsonPath("price").value(1000));
        BDDMockito.then(productService).should().findProduct(1L);
    }

    @Test
    void createProduct() throws Exception {
        // given
        BDDMockito
                .given(productService.saveProduct(any()))
                .willReturn(Product.builder()
                        .id(1L)
                        .name("pen")
                        .price(1000).build());

        // when
        AddProductRequest dto = new AddProductRequest("pen", 1000);

        ResultActions result = mockMvc.perform(MockMvcRequestBuilders
                .post("/api/v1/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)));

        // then
        result.andExpect(status().isOk())
                .andExpect(jsonPath("name").value("pen"))
                .andExpect(jsonPath("price").value(1000));
        BDDMockito.then(productService).should().saveProduct(any());
    }

    @Test
    void deleteProduct() throws Exception {
        // given
        BDDMockito
                .given(productService.deleteProduct(1L))
                .willReturn(Product.builder()
                        .id(1L)
                        .name("pen")
                        .price(1000).build());

        // when
        ResultActions result = mockMvc.perform(MockMvcRequestBuilders
                .delete("/api/v1/products/" + 1L));

        // then
        result.andExpect(status().isOk());
        BDDMockito.then(productService).should().deleteProduct(1L);
    }
}
