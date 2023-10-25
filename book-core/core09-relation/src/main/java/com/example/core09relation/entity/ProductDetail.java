package com.example.core09relation.entity;

import jakarta.persistence.*;
import lombok.Data;

// 짜파게티 정보
@Entity
@Data
public class ProductDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;

    @OneToOne
    private Product product;
}
