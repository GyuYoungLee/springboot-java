package com.example.core07test.dto;

import com.example.core07test.entity.Product;
import lombok.Getter;

@Getter   // response
public class ProductResponse {
    private final long id;
    private final String name;
    private final int price;

    public ProductResponse(Product product) {
        id = product.getId();
        name = product.getName();
        price = product.getPrice();
    }
}
