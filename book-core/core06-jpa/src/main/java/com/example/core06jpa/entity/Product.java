package com.example.core06jpa.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity               // db
@NoArgsConstructor    // db
@Getter               // entity -> dto
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Setter
    private Integer price;

    @Builder          // dto -> entity
    public Product(String name, Integer price) {
        this.name = name;
        this.price = price;
    }
}
