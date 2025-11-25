package com.newsfeed.domain.user.controller;

import com.newsfeed.common.exception.LoginFailException;
import com.newsfeed.common.response.GlobalResponse;
import com.newsfeed.domain.user.dto.deleteResponse.DeleteResponse;
import com.newsfeed.domain.user.dto.updatePasswordDto.UpdatePasswordRequest;
import com.newsfeed.domain.user.dto.userInfoDto.UserInfoResponse;
import com.newsfeed.domain.user.dto.updateDto.UpdateResponse;
import com.newsfeed.domain.user.dto.updateDto.UpdateRequest;

import com.newsfeed.domain.user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.newsfeed.common.exception.ErrorCode.LOGIN_REQUIRED;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final JwtProvider jwtProvider;

    private Long getUserId(HttpServletRequest loginId) {

    }

    //프로필 조회
    @GetMapping("{targetId}")
    public ResponseEntity<GlobalResponse<UserInfoResponse>> userInfo(HttpServletRequest loginId, @PathVariable Long targetId) {

        // 1. Authorization 헤더 체크
        String authHeader = loginId.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new LoginFailException(LOGIN_REQUIRED);
        }

        // 2. 토큰 추출
        String token = authHeader.replace("Bearer ", "");

        // 3. 토큰에서 userId 추출
        Long userId = jwtProvider.getUserIdFromToken(token);

        UserInfoResponse response = userService.userInfo(userId, targetId);

        // 4. 서비스 호출
        return ResponseEntity.ok(GlobalResponse.success(response));
    }

    //정보 수정
    @PutMapping("/me")
    public ResponseEntity<GlobalResponse<UpdateResponse>> update(HttpServletRequest tokenRequest, @Valid @RequestBody UpdateRequest upRequest) {

        // 1. Authorization 헤더 체크
        String authHeader = tokenRequest.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new LoginFailException(LOGIN_REQUIRED);
        }

        // 2. 토큰 추출
        String token = authHeader.replace("Bearer ", "");

        // 3. 토큰에서 userId 추출
        Long userId = jwtProvider.getUserIdFromToken(token);

        UpdateResponse response = userService.update(userId, upRequest);

        return ResponseEntity.ok(GlobalResponse.success(response));
    }

    //비밀번호 수정
    @PutMapping("/me/password")
    public ResponseEntity<GlobalResponse<UpdatePasswordResponse>> updatePassword(HttpServletRequest tokenRequest,@Valid @RequestBody UpdatePasswordRequest upRequest) {

        // 1. Authorization 헤더 체크
        String authHeader = tokenRequest.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new LoginFailException(LOGIN_REQUIRED);
        }

        // 2. 토큰 추출
        String token = authHeader.replace("Bearer ", "");

        // 3. 토큰에서 userId 추출
        Long userId = jwtProvider.getUserIdFromToken(token);

        UpdateResponse response = userService.updatePassword(userId, upRequest);

        return ResponseEntity.ok(GlobalResponse.success(response));
    }


    //회원탈퇴
    public ResponseEntity<GlobalResponse<DeleteResponse>> delete(HttpServletRequest tokenRequest) {

        // 1. Authorization 헤더 체크
        String authHeader = tokenRequest.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new LoginFailException(LOGIN_REQUIRED);
        }

        // 2. 토큰 추출
        String token = authHeader.replace("Bearer ", "");

        // 3. 토큰에서 userId 추출
        Long userId = jwtProvider.getUserIdFromToken(token);

        DeleteResponse response = userService.delete(userId);

        return ResponseEntity.ok(GlobalResponse.success(response));
    }

    // 유저 프로필 조회
//    @GetMapping("/other") public ResponseEntity<GlobalResponse<UserInfoResponse>> userInfo(HttpServletRequest request,
//                                                                           @RequestParam(required = false) String username,
//                                                                           @RequestParam(required = false) String email) {
//
//        // 1. Authorization 헤더 체크
//        String authHeader = request.getHeader("Authorization");
//        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
//        throw new LoginFailException(LOGIN_REQUIRED);
//        }
//
//        // 2. 토큰 추출
//        String token = authHeader.replace("Bearer ", "");
//
//        // 3. 토큰에서 userId 추출
//        Long userId = jwtProvider.getUserIdFromToken(token);
//
//        // 4. 서비스 호출
//        return ResponseEntity.ok(userService.searchOtherUser(username, email));
//    }

}
