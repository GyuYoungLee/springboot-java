package com.example.core07test.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;


@Entity
@NoArgsConstructor
@EqualsAndHashCode    // test (mockito)
@Getter               // entity -> dto
@ToString
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;

    @Builder          // dto -> entity
    public Article(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
