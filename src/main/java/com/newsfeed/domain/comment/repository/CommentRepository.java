package com.newsfeed.domain.comment.repository;


import com.newsfeed.common.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
