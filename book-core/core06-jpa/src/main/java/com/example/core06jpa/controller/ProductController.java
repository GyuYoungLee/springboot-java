package com.example.core06jpa.controller;

import com.example.core06jpa.dto.AddProductRequest;
import com.example.core06jpa.dto.ProductResponse;
import com.example.core06jpa.dto.UpdateProductRequest;
import com.example.core06jpa.entity.Product;
import com.example.core06jpa.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1")
@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/products/{id}")
    public ProductResponse getProduct(@PathVariable long id) {
        System.out.println("id = " + id);
        Product found = productService.findProduct(id);
        return new ProductResponse(found);
    }

    @PostMapping("/products")
    public ProductResponse createProduct(@RequestBody AddProductRequest dto) {
        System.out.println("dto = " + dto);
        Product created = productService.createProduct(dto.toEntity());
        return new ProductResponse(created);
    }

    @PutMapping("/products/{id}")
    public ProductResponse updateProduct(@PathVariable long id,
                                         @RequestBody UpdateProductRequest dto) {
        Product updated = productService.updateProductPrice(id, dto.getPrice());
        return new ProductResponse(updated);
    }

    @DeleteMapping("/products/{id}")
    public void deleteProduct(@PathVariable long id) {
        Product deleted = productService.deleteProduct(id);
    }
}
