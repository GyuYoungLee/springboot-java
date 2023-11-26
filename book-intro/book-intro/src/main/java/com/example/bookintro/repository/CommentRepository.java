package com.example.bookintro.repository;

import com.example.bookintro.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    // find by article_id
    List<Comment> findByArticleId(long articleId);
}
