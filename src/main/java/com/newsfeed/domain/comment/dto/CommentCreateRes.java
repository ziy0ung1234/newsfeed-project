package com.newsfeed.domain.comment.dto;

import com.newsfeed.common.entity.Comment;
import lombok.Getter;

@Getter
public class CommentCreateRes {
    private final String content;
    public CommentCreateRes(Comment comment) {
        this.content = comment.getContent();
    }
}
