package com.example.core09relation.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

// 농심
@Entity
@Data
public class Maker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "maker", fetch = FetchType.EAGER)
    @ToString.Exclude
    private List<Product> products = new ArrayList<>();
}
