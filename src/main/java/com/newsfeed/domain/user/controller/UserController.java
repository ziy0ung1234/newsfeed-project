package com.newsfeed.domain.user.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final JwtProvider jwtProvider;

    //프로필 조회
    @GetMapping("/me")
    public ResponseEntity<GlobalResponse<MyInfoResponse>> myInfo(HttpServletRequest request) {

        // 1. Authorization 헤더 체크
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new IllegalArgumentException("로그인이 유효 하지 않습니다.");
        }

        // 2. 토큰 추출
        String token = authHeader.replace("Bearer ", "");

        // 3. 토큰에서 userId 추출
        Long userId = jwtProvider.getUserIdFromToken(token);

        // 4. 서비스 호출
        return ResponseEntity.ok(userService.myInfo(userId));
    }

    //정보 수정
    @PutMapping("/me")
    public ResponseEntity<GlobalResponse<UpdateResponse>> update(HttpServletRequest tokenRequest, @Valid @RequestBody UpdateRequest upRequest) {
        // 1. Authorization 헤더 체크
        String authHeader = tokenRequest.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new IllegalArgumentException("로그인이 유효 하지 않습니다.");
        }

        // 2. 토큰 추출
        String token = authHeader.replace("Bearer", "");

        // 3. 토큰에서 userId 추출
        Long userId = jwtProvider.getUserIdFromToken(token);

        return ResponseEntity.ok(userService.update(userId,upRequest));
    }


    //회원탈퇴
    public ResponseEntity<GlobalResponse<DeleteResponse>> delete(HttpServletRequest tokenRequest) {

        // 1. Authorization 헤더 체크
        String authHeader = tokenRequest.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new IllegalArgumentException("로그인이 유효 하지 않습니다.");
        }

        // 2. 토큰 추출
        String token = authHeader.replace("Bearer", "");

        // 3. 토큰에서 userId 추출
        Long userId = jwtProvider.getUserIdFromToken(token);

        return ResponseEntity.ok(userId);
    }

    // 유저 프로필 조회
//    @GetMapping("/other") public ResponseEntity<GlobalResponse<UserInfoResponse>> userInfo(HttpServletRequest request,
//                                                                           @RequestParam(required = false) String username,
//                                                                           @RequestParam(required = false) String email) {
//
//        // 1. Authorization 헤더 체크
//        String authHeader = request.getHeader("Authorization");
//        if (authHeader == null || !authHeader.startsWith("Bearer ")) { throw new IllegalArgumentException("로그인이 유효 하지 않습니다."); }
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
