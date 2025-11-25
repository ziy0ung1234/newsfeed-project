package com.newsfeed.domain.comment.dto;

import lombok.Getter;

@Getter
public class CommentPutRequest {
    private String content;
    CommentPutRequest(String content) {
        this.content = content;
    }
}
