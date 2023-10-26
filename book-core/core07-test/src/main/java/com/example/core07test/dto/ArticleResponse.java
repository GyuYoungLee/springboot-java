package com.example.core07test.dto;

import com.example.core07test.entity.Article;
import lombok.Getter;

@Getter   // response
public class ArticleResponse {
    private final Long id;
    private final String title;
    private final String content;

    public ArticleResponse(Article article) {
        id = article.getId();
        title = article.getTitle();
        content = article.getContent();
    }
}
