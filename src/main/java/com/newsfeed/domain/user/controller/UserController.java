package com.newsfeed.domain.user.controller;

import com.newsfeed.common.exception.LoginFailException;
import com.newsfeed.common.response.GlobalResponse;
import com.newsfeed.domain.user.dto.deleteResponse.DeleteResponse;
import com.newsfeed.domain.user.dto.updatePasswordDto.UpdatePasswordRequest;
import com.newsfeed.domain.user.dto.updatePasswordDto.UpdatePasswordResponse;
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
//    private final JwtProvider jwtProvider;

    private Long getUserId(HttpServletRequest loginId) {
        String authHeader = loginId.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new LoginFailException(LOGIN_REQUIRED);
        }
        String token = authHeader.substring(7);
        return null;
//                jwtProvider.getUserIdFromToken(token);
    }

    //프로필 조회
    @GetMapping("/{id}")
    public ResponseEntity<GlobalResponse<UserInfoResponse>> userInfo(HttpServletRequest request, @PathVariable Long targetId) {
            Long userId = getUserId(request);
            UserInfoResponse response = userService.userInfo(userId, targetId);
        return ResponseEntity.ok(GlobalResponse.success(200,"프로필 조회가 완료 되었습니다.",response));
    }

    //정보 수정
    @PutMapping("/me")
    public ResponseEntity<GlobalResponse<UpdateResponse>> update(HttpServletRequest request, @Valid @RequestBody UpdateRequest upRequest) {
            Long userId = getUserId(request);
            UpdateResponse response = userService.update(userId, upRequest);
        return ResponseEntity.ok(GlobalResponse.success(200,"개인정보가 수정 되었습니다.",response));
    }

    //비밀번호 수정
    @PutMapping("/me/password")
    public ResponseEntity<GlobalResponse<UpdatePasswordResponse>> updatePassword(HttpServletRequest request, @Valid @RequestBody UpdatePasswordRequest upRequest) {
            Long userId = getUserId(request);
            UpdatePasswordResponse response = userService.updatePassword(userId, upRequest);
        return ResponseEntity.ok(GlobalResponse.success(200,"비밀번호가 변경 되었습니다.",response));
    }


    //회원탈퇴
    @DeleteMapping("/me")
    public ResponseEntity<GlobalResponse<DeleteResponse>> delete(HttpServletRequest request) {
            Long userId = getUserId(request);
            DeleteResponse response = userService.delete(userId);
        return ResponseEntity.ok(GlobalResponse.success(200,"회원 탈퇴가 완료되었습니다.",response));
    }

}

