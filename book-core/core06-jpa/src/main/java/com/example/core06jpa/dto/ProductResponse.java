package com.example.core06jpa.dto;

import com.example.core06jpa.entity.Product;
import lombok.Getter;

@Getter              // response
public class ProductResponse {
    private final Long id;
    private final String name;
    private final Integer price;

    public ProductResponse(Product product) {
        id = product.getId();
        name = product.getName();
        price = product.getPrice();
    }
}
