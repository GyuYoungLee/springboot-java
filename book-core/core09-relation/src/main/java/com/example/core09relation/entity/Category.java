package com.example.core09relation.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

// 라면
@Entity
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String code;
    private String name;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name="category_id")
    private List<Product> products = new ArrayList<>();
}
