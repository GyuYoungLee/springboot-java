package com.example.bookintro.service;

import com.example.bookintro.entity.Comment;
import com.example.bookintro.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    // 생성
    public Comment createComment(Comment comment) {
        return commentRepository.save(comment);
    }

    // 조회
    public List<Comment> getCommentList(long articleId) {
        return commentRepository.findByArticleId(articleId);
    }

    // 수정
    public Comment updateComment(long id, Comment comment) {
        Comment target = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글 없음"));

        target.setNickname(comment.getNickname());
        target.setBody(comment.getBody());
        return commentRepository.save(target);
    }

    // 삭제
    public Comment deleteComment(long id) {
        Comment target = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 댓글 없음"));

        commentRepository.delete(target);
        return target;
    }
}
