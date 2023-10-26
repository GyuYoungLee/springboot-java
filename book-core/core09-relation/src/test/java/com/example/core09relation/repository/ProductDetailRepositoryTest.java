package com.example.core09relation.repository;

import com.example.core09relation.entity.Product;
import com.example.core09relation.entity.ProductDetail;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProductDetailRepositoryTest {
    @Autowired
    private ProductDetailRepository productDetailRepository;
    @Autowired
    private ProductRepository productRepository;

    @Test
    public void testRelation() {
        Product product = new Product();
        product.setName("짜파게티");
        product.setPrice(1000);
        productRepository.save(product);

        ProductDetail productDetail = new ProductDetail();
        productDetail.setDescription("맛있는 짜파게티");
        productDetail.setProduct(product);               // productDetail -> product
        productDetailRepository.save(productDetail);

        // 단방향
        System.out.println(productDetailRepository.findById(productDetail.getId()).get()
                .getProduct());

        // 양방향
        System.out.println(productRepository.findById(product.getId()).get()
                .getProductDetail());
    }
}
