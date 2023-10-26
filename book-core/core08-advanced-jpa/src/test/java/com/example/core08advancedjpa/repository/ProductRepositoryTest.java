package com.example.core08advancedjpa.repository;

import com.example.core08advancedjpa.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProductRepositoryTest {
    @Autowired
    private ProductRepository productRepository;

    @Test
    void testProductRepositoryMethod() {
        productRepository.save(Product.builder().name("water").price(1000).build());
        productRepository.save(Product.builder().name("milk").price(2000).build());
        productRepository.save(Product.builder().name("beer").price(3000).build());

        // 쿼리 메서드
        System.out.println(productRepository.findByPriceIsBetween(2000, 5000));

        // @Query 어노테이션
        System.out.println(productRepository.findByPrice(2000));

        // QueryDSL
        System.out.println(productRepository.findByName("water"));
    }
}
