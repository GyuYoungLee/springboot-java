package com.example.core07test.repository;

import com.example.core07test.entity.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
class ProductRepositoryTest {
    @Autowired
    private ProductRepository productRepository;

    @BeforeEach
    public void setUp() {
        productRepository.deleteAll();
    }

    @Test
    void findByName() {
        // given
        productRepository.save(Product.builder()
                .name("pen")
                .price(1000).build());

        // when
        List<Product> productList = productRepository.findByName("pen");

        // then
        assertEquals("pen", productList.get(0).getName());
        assertEquals(1000, productList.get(0).getPrice());
    }
}
