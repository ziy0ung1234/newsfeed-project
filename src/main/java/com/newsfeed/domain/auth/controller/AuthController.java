package com.newsfeed.domain.auth.controller;

import com.newsfeed.common.response.GlobalResponse;
import com.newsfeed.domain.auth.dto.LoginRequest;
import com.newsfeed.domain.auth.dto.SignUpRequest;
import com.newsfeed.domain.auth.dto.TokenResponse;
import com.newsfeed.domain.auth.security.PrincipalDetails;
import com.newsfeed.domain.auth.service.AuthService;
import com.newsfeed.domain.user.entity.User;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<GlobalResponse<Void>> signUp(@Valid @RequestBody SignUpRequest request) {

        authService.signUp(request);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(GlobalResponse.successNodata(200, "회원가입이 완료되었습니다."));
    }

    @PostMapping("/login")
    public ResponseEntity<GlobalResponse<TokenResponse>> login(@RequestBody LoginRequest request, HttpServletResponse response) {

        TokenResponse token = authService.login(request);

        // 클라이언트에 전달해줄 토큰 헤더에 담기
        response.setHeader("Authorization", "Bearer " + token.getAccessToken());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(GlobalResponse.success(200, "로그인이 완료되었습니다.", token));
    }

    @DeleteMapping("/logout")
    public ResponseEntity<GlobalResponse<Void>> logout() {

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(GlobalResponse.successNodata(200, "로그아웃 완료"));
    }

    // 실행 예시 코드
    // 1. api의 매개변수에 @AuthenticationPrincipal PrincipalDetails principalDetails 를 넣는다.
    // 2. 그러면 Spring Security 저장소에서 PrincipalDetails 객체에 User 객체를 담아서 넘겨준다.
    // 3. 그러면 principalDetails 객체 안에있는 getter로 유저 정보를 갖다 쓰면 된다.
    @GetMapping("/me")
    public ResponseEntity<GlobalResponse<User>> getMe(@AuthenticationPrincipal PrincipalDetails principalDetails) {

        User user = principalDetails.getUser();
//        System.out.println(user);
//        System.out.println(user.getId());
//        System.out.println(user.getEmail());
//        System.out.println(user.getCellPhoneNumber());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(GlobalResponse.success(200, "유저 정보 조회 성공", user));
    }

}
