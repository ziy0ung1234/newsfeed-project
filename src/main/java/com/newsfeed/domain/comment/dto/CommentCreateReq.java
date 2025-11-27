package com.newsfeed.domain.comment.dto;

import lombok.Getter;

@Getter
public class CommentCreateReq {
    private final String content;
    public CommentCreateReq(String content) {
        this.content = content;
    }
}
