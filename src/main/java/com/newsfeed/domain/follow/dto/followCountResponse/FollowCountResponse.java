package com.newsfeed.domain.follow.dto.followCountResponse;

import lombok.Getter;

/**
 * FollowCountResponse
 * - 팔로워·팔로잉 숫자 정보 DTO
 */
@Getter
public class FollowCountResponse {

    private final Long followingCount; // 내가 팔로우한 사람 수
    private final Long followerCount; // 나를 팔로우한 사람 수

    private FollowCountResponse(Long followingCount, Long followerCount) {
        this.followingCount = followingCount;
        this.followerCount = followerCount;
    }

    public static FollowCountResponse of(Long following, Long follower) {
        return new FollowCountResponse(
                following
                ,follower);
    }
}
