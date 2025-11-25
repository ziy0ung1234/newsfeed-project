package com.newsfeed.domain.comment.dto;

import lombok.Getter;

@Getter
public class CommentCreateReq {
    private final Long userId;
    private final String content;
    public CommentCreateReq(Long userId, String content) {
        this.userId = userId;
        this.content = content;
    }
}
