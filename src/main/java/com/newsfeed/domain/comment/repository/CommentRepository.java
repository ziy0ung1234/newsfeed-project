package com.newsfeed.domain.comment.repository;

import com.newsfeed.common.exception.ErrorCode;
import com.newsfeed.common.exception.NotFoundException;
import com.newsfeed.domain.comment.entity.Comment;
import com.newsfeed.domain.newsfeed.entity.Newsfeed;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    default Comment findOrThrow(Long commentId) {
        return findById(commentId)
                .orElseThrow(()-> new NotFoundException(ErrorCode.COMMENT_NOT_FOUND));
    }
}
