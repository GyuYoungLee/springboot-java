package com.example.core06jpa.service;

import com.example.core06jpa.entity.Product;
import com.example.core06jpa.respository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Product findProduct(long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 상품 없음"));
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProductPrice(long id, int price) {
        Product target = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 상품 없음"));

        target.setPrice(price);
        return productRepository.save(target);
    }

    public Product deleteProduct(long id) {
        Product target = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 상품 없음"));

        productRepository.delete(target);
        return target;
    }
}
