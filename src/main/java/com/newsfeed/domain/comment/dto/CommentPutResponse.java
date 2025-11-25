package com.newsfeed.domain.comment.dto;

import com.newsfeed.domain.comment.entity.Comment;
import lombok.Getter;

@Getter
public class CommentPutResponse {
    private final Long id;
    private final String content;
    public CommentPutResponse(Comment comment) {
        this.id = comment.getId();
        this.content = comment.getContent();
    }
}
