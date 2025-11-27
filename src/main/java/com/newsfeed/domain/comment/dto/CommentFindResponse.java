package com.newsfeed.domain.comment.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.newsfeed.domain.comment.entity.Comment;
import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommentFindResponse {
    private final Long id;
    private final String userName;
    private final Long newsfeedId;
    private final String content;
    private final int depth;
    private final Long parentCommentId;

    public CommentFindResponse(Comment comment) {
        this.id = comment.getId();
        this.content = comment.getContent();
        this.depth = comment.getDepth();
        this.parentCommentId = (comment.getParentCommentId()==null) ? null : comment.getParentCommentId().getId();
        this.userName = comment.getUserId().getUsername();
        this.newsfeedId = comment.getNewsfeedId().getId();
    }
}
