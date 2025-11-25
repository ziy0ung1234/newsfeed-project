package com.newsfeed.comment.dto;

import com.newsfeed.common.entity.Comment;
import com.newsfeed.common.entity.Newsfeed;
import com.newsfeed.common.entity.User;
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
