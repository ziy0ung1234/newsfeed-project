package com.newsfeed.domain.comment.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.newsfeed.domain.comment.entity.Comment;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommentWithChildrenResponse {
    private final Long id;
    private final String userName;
    private final Long newsfeedId;
    private final String content;
    private final int depth;
    private final Long parentCommentId;
    private List<CommentWithChildrenResponse> children = new ArrayList<>();

    public CommentWithChildrenResponse(Comment comment) {
        this.id = comment.getId();
        this.content = comment.getContent();
        this.depth = comment.getDepth();
        this.parentCommentId = (comment.getParentCommentId()==null) ? null : comment.getParentCommentId().getId();
        this.userName = comment.getUserId().getUsername();
        this.newsfeedId = comment.getNewsfeedId().getId();
    }

    public void addChild(CommentWithChildrenResponse children) {
        this.children.add(children);
    }
}
