package com.example.core07test.controller;


import com.example.core07test.dto.AddProductRequest;
import com.example.core07test.dto.ProductResponse;
import com.example.core07test.entity.Product;
import com.example.core07test.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1")
@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/products/{id}")
    public ProductResponse getProduct(@PathVariable long id) {
        Product found = productService.findProduct(id);
        return new ProductResponse(found);
    }

    @PostMapping("/products")
    public ProductResponse createProduct(@RequestBody AddProductRequest dto) {
        Product saved = productService.saveProduct(dto.toEntity());
        return new ProductResponse(saved);
    }

    @DeleteMapping("/products/{id}")
    public void deleteProduct(@PathVariable long id) {
        Product deleted = productService.deleteProduct(id);
    }
}
