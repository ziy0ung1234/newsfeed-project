package com.newsfeed.domain.follow.followResponse;

import lombok.Getter;

@Getter
public class FollowResponse {

    private final Long id;
    private final Long userId;
    private final Long otherId;

    public FollowResponse(Long id, Long userId, Long otherId) {
        this.id = id;
        this.userId = userId;
        this.otherId = otherId;
    }
}
