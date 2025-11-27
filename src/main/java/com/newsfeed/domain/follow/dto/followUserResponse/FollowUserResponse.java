package com.newsfeed.domain.follow.dto.followUserResponse;

import com.newsfeed.domain.user.entity.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * FollowUserResponse
 * - 팔로우/팔로워 목록에서 보여줄 상대방 유저 정보 DTO
 */
@Getter
@RequiredArgsConstructor
public class FollowUserResponse {
    private final Long userId;
    private final String userName;
    private final String email;

    public static FollowUserResponse of(User user) {
        return new FollowUserResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail()
        );
    }

}
