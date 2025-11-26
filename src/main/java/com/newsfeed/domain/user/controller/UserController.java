package com.newsfeed.domain.user.controller;

import com.newsfeed.common.response.GlobalResponse;
import com.newsfeed.domain.auth.security.PrincipalDetails;
import com.newsfeed.domain.user.dto.updatePasswordDto.UpdatePasswordRequest;
import com.newsfeed.domain.user.dto.updatePasswordDto.UpdatePasswordResponse;
import com.newsfeed.domain.user.dto.userInfoDto.UserInfoResponse;
import com.newsfeed.domain.user.dto.updateDto.UpdateResponse;
import com.newsfeed.domain.user.dto.updateDto.UpdateRequest;

import com.newsfeed.domain.user.entity.User;
import com.newsfeed.domain.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

/**
 * 유저 관련 API 컨트롤러.
 * 프로필 조회, 정보 수정, 비밀번호 변경, 회원 탈퇴 기능을 제공한다.
 * 인증 정보는 @AuthenticationPrincipal 로 받아 처리한다.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    /**
     * 프로필 조회 (본인 + 다른 유저)
     */
    @GetMapping("/{userid}")
    public ResponseEntity<GlobalResponse<UserInfoResponse>> userInfo(@AuthenticationPrincipal PrincipalDetails principalDetails, @PathVariable Long userid) {
        User user = principalDetails.getUser();
        Long userId = user.getId();
        UserInfoResponse response = userService.userInfo(userId, userid);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(GlobalResponse.success(200, "프로필 조회가 완료 되었습니다.", response));
    }

    /**
     * 내 정보 수정
     */
    @PutMapping("/me")
    public ResponseEntity<GlobalResponse<UpdateResponse>> update(@AuthenticationPrincipal PrincipalDetails principalDetails, @Valid @RequestBody UpdateRequest upRequest) {
        User user = principalDetails.getUser();
        Long userId = user.getId();
        UpdateResponse response = userService.update(userId, upRequest);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(GlobalResponse.success(200, "개인정보가 수정 되었습니다.", response));
    }

    /**
     * 비밀번호 변경
     */
    @PutMapping("/me/password")
    public ResponseEntity<GlobalResponse<UpdatePasswordResponse>> updatePassword(@AuthenticationPrincipal PrincipalDetails principalDetails, @Valid @RequestBody UpdatePasswordRequest upRequest) {
        User user = principalDetails.getUser();
        Long userId = user.getId();
        UpdatePasswordResponse response = userService.updatePassword(userId, upRequest);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(GlobalResponse.success(200, "비밀번호가 변경 되었습니다.", response));
    }

    /**
     * 회원 탈퇴
     */
    @DeleteMapping("/me")
    public ResponseEntity<GlobalResponse<Void>> delete(@AuthenticationPrincipal PrincipalDetails principalDetails) {
        User user = principalDetails.getUser();
        Long userId = user.getId();

        userService.delete(userId);

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body(GlobalResponse.successNodata(204, "회원 탈퇴가 완료되었습니다."));
    }

}

