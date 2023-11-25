package com.example.bookintro.dto.response;

import com.example.bookintro.entity.Article;
import lombok.Getter;

@Getter
public class ArticleRes {
    private final long id;
    private final String title;
    private final String content;

    // entity -> dto
    public ArticleRes(Article article) {
        id = article.getId();
        title = article.getTitle();
        content = article.getContent();
    }
}
