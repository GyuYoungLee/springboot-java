package com.example.core07test.service;

import com.example.core07test.entity.Product;
import com.example.core07test.repository.ProductRepository;
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

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public Product deleteProduct(long id) {
        Product target = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 상품 없음"));

        productRepository.delete(target);
        return target;
    }
}
