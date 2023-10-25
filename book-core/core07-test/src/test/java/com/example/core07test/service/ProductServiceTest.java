package com.example.core07test.service;

import com.example.core07test.entity.Product;
import com.example.core07test.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;

@TestConfiguration
@ExtendWith(SpringExtension.class)
@Import(ProductService.class)
class ProductServiceTest {
    @Autowired
    private ProductService productService;
    @MockBean
    private ProductRepository productRepository;

    @Test
    void findProduct() {
        // given
        BDDMockito
                .given(productRepository.findById(1L))
                .willReturn(Optional.of(Product.builder()
                        .name("pen")
                        .price(1000).build()));

        // when
        Product found = productService.findProduct(1L);

        // then
        assertEquals("pen", found.getName());
        assertEquals(1000, found.getPrice());
        BDDMockito.then(productRepository).should().findById(1L);
    }

    @Test
    void findProduct_exception() {
        // given
        BDDMockito
                .given(productRepository.findById(-1L))
                .willReturn(Optional.empty());

        // when
        assertThrows(IllegalArgumentException.class, () -> {
            productService.findProduct(-1L);
        });

        // then
        BDDMockito.then(productRepository).should().findById(-1L);
    }

    @Test
    void saveProduct() {
        // given
        BDDMockito
                .given(productRepository.save(any()))
                .willAnswer(returnsFirstArg());

        // when
        Product saved = productService.saveProduct(Product.builder()
                .name("pen")
                .price(1000).build());

        // then
        assertEquals("pen", saved.getName());
        assertEquals(1000, saved.getPrice());
        BDDMockito.then(productRepository).should().save(any());
    }

    @Test
    void deleteProduct() {
        // given
        Product product = Product.builder()
                .id(1L)
                .name("pen")
                .price(1000).build();

        BDDMockito
                .given(productRepository.findById(1L))
                .willReturn(Optional.of(product));
        BDDMockito
                .willDoNothing()
                .given(productRepository).delete(product);

        // when
        Product deleted = productService.deleteProduct(1L);

        // then
        assertEquals("pen", deleted.getName());
        assertEquals(1000, deleted.getPrice());
        BDDMockito.then(productRepository).should().findById(1L);
        BDDMockito.then(productRepository).should().delete(product);
    }

    @Test
    void deleteProduct_exception() {
        // given
        BDDMockito
                .given(productRepository.findById(-1L))
                .willReturn(Optional.empty());

        // when
        assertThrows(IllegalArgumentException.class, () -> {
            productService.deleteProduct(-1L);
        });

        // then
        BDDMockito.then(productRepository).should().findById(-1L);
    }
}
