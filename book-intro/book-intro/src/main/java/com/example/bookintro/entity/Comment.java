package com.example.bookintro.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@Getter
@ToString
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter
    private String nickname;
    @Setter
    private String body;

    @ManyToOne
    private Article article;

    @Builder
    public Comment(Long id, String nickname, String body, Article article) {
        this.id = id;
        this.nickname = nickname;
        this.body = body;
        this.article = article;
    }
}
