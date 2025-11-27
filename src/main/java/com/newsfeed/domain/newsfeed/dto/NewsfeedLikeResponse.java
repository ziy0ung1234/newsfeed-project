package com.newsfeed.domain.newsfeed.dto;

import com.newsfeed.domain.newsfeed.entity.Newsfeed;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class NewsfeedLikeResponse {
    private final Long id;
    private final String title;
    private final String content;
    private final LocalDateTime createAt;
    private final LocalDateTime updateAt;
    private final int likeCount;

    public NewsfeedLikeResponse(Newsfeed newsfeed, int likeCount) {
        this.id = newsfeed.getId();
        this.title = newsfeed.getTitle();
        this.content = newsfeed.getContent();
        this.createAt = newsfeed.getCreatedAt();
        this.updateAt = newsfeed.getModifiedAt();
        this.likeCount = likeCount;
    }
}
