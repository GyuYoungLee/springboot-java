package com.example.core09relation.repository;

import com.example.core09relation.entity.Category;
import com.example.core09relation.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CategoryRepositoryTest {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductRepository productRepository;

    @Test
    public void testRelation() {
        Product product = new Product();
        product.setName("짜파게티");
        product.setPrice(2000);
        productRepository.save(product);

        Category category = new Category();
        category.setCode("S1");
        category.setName("라면");
        category.getProducts().add(product);   // category -> product
        categoryRepository.save(category);

        // 단방향
        System.out.println(categoryRepository.findById(category.getId()).get()
                .getProducts());
    }
}
