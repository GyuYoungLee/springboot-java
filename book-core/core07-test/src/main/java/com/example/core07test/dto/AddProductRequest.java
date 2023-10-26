package com.example.core07test.dto;

import com.example.core07test.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor   // request
@Getter               // swagger
@ToString
public class AddProductRequest {
    private String name;
    private int price;

    public Product toEntity() {
        return Product.builder()
                .name(name)
                .price(price)
                .build();
    }
}
