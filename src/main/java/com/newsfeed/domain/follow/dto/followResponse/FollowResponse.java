package com.newsfeed.domain.follow.dto.followResponse;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * FollowResponse
 * - 팔로우 성공 시 반환되는 단순 응답 DTO
 * - 누가(userId) 누구(otherId)를 팔로우했는지 반환
 */
@Getter
@RequiredArgsConstructor
public class FollowResponse {

    private final Long id;       // 팔로우 관계 PK (Follow 엔티티의 id)
    private final Long userId;   // 팔로우 요청한 유저 (나)
    private final Long otherId;  // 팔로우 당하는 유저 (상대방)

}
