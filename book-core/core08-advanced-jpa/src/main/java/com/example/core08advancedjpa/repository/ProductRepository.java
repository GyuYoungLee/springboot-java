package com.example.core08advancedjpa.repository;

import com.example.core08advancedjpa.entity.Product;
import com.example.core08advancedjpa.repository.querydsl.ProductRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long>, ProductRepositoryCustom {

        // 쿼리 메서드
        List<Product> findByPriceIsBetween(Integer lowPrice, Integer highPrice);

        // @Query 어노테이션
        @Query("SELECT p FROM Product p WHERE p.price = :price")
        List<Product> findByPrice(@Param("price") Integer price);
}
