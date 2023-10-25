package com.example.core08advancedjpa.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@ToString
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Setter
    private Integer price;

    // dto -> entity
    @Builder
    public Product(Long id, String name, Integer price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
}
