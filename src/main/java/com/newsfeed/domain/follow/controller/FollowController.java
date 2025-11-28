package com.newsfeed.domain.follow.controller;

import com.newsfeed.common.response.GlobalResponse;
import com.newsfeed.domain.auth.security.PrincipalDetails;
import com.newsfeed.domain.follow.dto.followCountResponse.FollowCountResponse;
import com.newsfeed.domain.follow.dto.followResponse.FollowResponse;
import com.newsfeed.domain.follow.dto.followUserResponse.FollowUserResponse;
import com.newsfeed.domain.follow.service.FollowService;
import com.newsfeed.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * FollowController
 * - 유저 간 팔로우/언팔로우/조회 기능을 담당하는 API 컨트롤러
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class FollowController {

    private final FollowService followService;

    /**
     * 팔로우 등록
     * (userId → otherId)
     * 내가(otherId)를 팔로우함
     */
    @PostMapping("/{otherId}/follows")
    public ResponseEntity<GlobalResponse<FollowResponse>> follow(@AuthenticationPrincipal PrincipalDetails principalDetails, @PathVariable Long otherId) {
        User user = principalDetails.getUser();
        followService.follow(user, otherId);
        FollowResponse response = new FollowResponse(otherId, user.getId(), otherId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(GlobalResponse.success(200, "[유저ID : " + otherId + "번의 유저를 팔로우 했습니다]", response));
    }

    /**
     * 언팔로우
     * (userId → otherId 관계 삭제)
     */
    @DeleteMapping("{otherId}/unfollows")
    public ResponseEntity<GlobalResponse<Void>> unfollow(@AuthenticationPrincipal PrincipalDetails principalDetails, @PathVariable Long otherId) {
        Long userId = principalDetails.getUser().getId();
        followService.unfollow(userId, otherId);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body(GlobalResponse.successNodata(204, "[유저ID : " + otherId + "번의 유저를 언팔로우 했습니다]"));
    }

    /**
     * 팔로우·팔로워 수 조회
     * followingCount = 내가 팔로우한 사람 수
     * followerCount  = 나를 팔로우한 사람 수
     */
    @GetMapping("/me/Followers")
    public ResponseEntity<GlobalResponse<FollowCountResponse>> followers(@AuthenticationPrincipal PrincipalDetails principalDetails) {
        User user = principalDetails.getUser();
        Long userId = user.getId();
        FollowCountResponse response = followService.followers(userId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(GlobalResponse.success(200, "팔로우 정보 조회 완료", response));
    }

    /**
     * 팔로잉 목록 조회
     * = 내가 팔로우 “하고 있는” 사람들 리스트
     */
    @GetMapping("/me/following")
    public ResponseEntity<GlobalResponse<List<FollowUserResponse>>> followerUsers(@AuthenticationPrincipal PrincipalDetails principalDetails) {
        Long userId = principalDetails.getUser().getId();
        List<FollowUserResponse> responses =followService.followingList(userId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(GlobalResponse.success(200,"팔로잉 목록 조회 완료", responses));
    }

    /**
     * 팔로워 목록 조회
     * = 나를 팔로우 “하고 있는” 사람들 리스트
     */
    @GetMapping("/me/followers")
    public ResponseEntity<GlobalResponse<List<FollowUserResponse>>> followingUsers(@AuthenticationPrincipal PrincipalDetails principalDetails) {
        Long userId = principalDetails.getUser().getId();
        List<FollowUserResponse> responses =followService.followList(userId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(GlobalResponse.success(200,"팔로워 목록 조회 완료", responses));
    }
}
