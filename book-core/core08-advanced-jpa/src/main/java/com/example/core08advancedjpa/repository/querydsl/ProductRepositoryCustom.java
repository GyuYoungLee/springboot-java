package com.example.core08advancedjpa.repository.querydsl;

import com.example.core08advancedjpa.entity.Product;

import java.util.List;

public interface ProductRepositoryCustom {

    // QueryDSL
    List<Product> findByName(String name);
}
