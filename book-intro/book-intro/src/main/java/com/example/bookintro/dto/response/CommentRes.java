package com.example.bookintro.dto.response;

import com.example.bookintro.entity.Comment;
import lombok.Getter;

@Getter
public class CommentRes {
    private final long id;
    private final String nickname;
    private final String body;
    private final long articleId;

    // entity -> dto
    public CommentRes(Comment comment) {
        id = comment.getId();
        nickname = comment.getNickname();
        body = comment.getBody();
        articleId = comment.getArticle().getId();
    }
}
