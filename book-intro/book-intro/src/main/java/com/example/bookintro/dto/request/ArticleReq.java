package com.example.bookintro.dto.request;

import com.example.bookintro.entity.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class ArticleReq {
    private String title;
    private String content;

    // dto -> entity
    public Article toEntity() {
        return Article.builder()
                .title(title)
                .content(content)
                .build();
    }
}
