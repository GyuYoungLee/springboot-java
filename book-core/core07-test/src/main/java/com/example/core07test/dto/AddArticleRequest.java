package com.example.core07test.dto;

import com.example.core07test.entity.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor   // request
@Getter               // swagger
@ToString
public class AddArticleRequest {
    private String title;
    private String content;

    public Article toEntity() {
        return Article.builder()
                .title(title)
                .content(content)
                .build();
    }
}
