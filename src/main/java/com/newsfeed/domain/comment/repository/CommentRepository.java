package com.newsfeed.domain.comment.repository;

import com.newsfeed.common.exception.ErrorCode;
import com.newsfeed.common.exception.NotFoundException;
import com.newsfeed.domain.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByParentCommentId_Id(Long parentCommentIdId);
    default Comment findOrThrow(Long commentId) {
        return findById(commentId)
                .orElseThrow(()-> new NotFoundException(ErrorCode.COMMENT_NOT_FOUND));
    }
}
