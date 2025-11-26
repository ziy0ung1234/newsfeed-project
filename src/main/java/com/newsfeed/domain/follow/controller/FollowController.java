package com.newsfeed.domain.follow.controller;

import com.newsfeed.common.response.GlobalResponse;
import com.newsfeed.domain.auth.security.PrincipalDetails;
import com.newsfeed.domain.follow.followResponse.FollowResponse;
import com.newsfeed.domain.follow.service.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class FollowController {

    private final FollowService followService;

    //팔로우 등록
    @PostMapping("/{otherId}/followes")
    public ResponseEntity<GlobalResponse<FollowResponse>> follow(@AuthenticationPrincipal PrincipalDetails principalDetails, @PathVariable Long otherId) {
        Long userId = principalDetails.getUser().getId();

        FollowResponse response = new FollowResponse(userId,userId,otherId);
        followService.follow(userId, otherId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(GlobalResponse.success(200, "[유저ID : " +  otherId + "번의 유저를 팔로우 했습니다]",response));
    }

    //팔로우 취소
    @DeleteMapping("{otherId}/unfollowes")
    public ResponseEntity<GlobalResponse<Void>> unfollow(@AuthenticationPrincipal PrincipalDetails principalDetails, @PathVariable Long otherId) {
        Long userId = principalDetails.getUser().getId();
        followService.unfollow(userId, otherId);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body(GlobalResponse.successNodata(204, "[유저ID : " +  otherId + "번의 유저를 언팔로우 했습니다]"));
    }

}
