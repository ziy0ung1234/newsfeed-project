package com.newsfeed.domain.follow;

import com.newsfeed.common.response.GlobalResponse;
import com.newsfeed.domain.auth.security.PrincipalDetails;
import com.newsfeed.domain.follow.entity.Follow;
import com.newsfeed.domain.user.dto.userInfoDto.UserInfoResponse;
import com.newsfeed.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class FollowController {

    private final FollowService followService;

    //팔로우 등록
    @PostMapping("/{userId}/followes")
    public ResponseEntity<GlobalResponse<Void>> follow(@AuthenticationPrincipal PrincipalDetails principalDetails, @PathVariable Long otherId) {
        User user = principalDetails.getUser();
        Long userId = user.getId();
        followService.follow(userId, otherId);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body(GlobalResponse.successNodata(204, "[유저ID : " +  otherId + "번의 유저를 팔로우 했습니다]"));
    }

    //팔로우 취소
}
