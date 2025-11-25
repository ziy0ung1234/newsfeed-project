package com.newsfeed.domain.comment.dto;

import com.newsfeed.common.entity.Comment;
import com.newsfeed.domain.newsfeed.entity.Newsfeed;
import com.newsfeed.domain.user.entity.User;
import lombok.Getter;

@Getter
public class CommentGetResponse {
    private final Long id;
    private final String userName;
    private final Long newsfeedId;
    private final String content;
    private final int depth;

    public CommentGetResponse(Comment comment, User user, Newsfeed newsfeed) {
        this.id = comment.getId();
        this.content = comment.getContent();
        this.depth = comment.getDepth();
        this.userName = user.getUsername();
        this.newsfeedId = newsfeed.getId();
    }
}
