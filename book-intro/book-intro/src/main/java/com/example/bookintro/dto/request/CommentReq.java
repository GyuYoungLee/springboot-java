package com.example.bookintro.dto.request;

import com.example.bookintro.entity.Article;
import com.example.bookintro.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class CommentReq {
    private String nickname;
    private String body;

    // dto -> entity
    public Comment toEntity(Article article) {
        return Comment.builder()
                .nickname(nickname)
                .body(body)
                .article(article).build();
    }
}
