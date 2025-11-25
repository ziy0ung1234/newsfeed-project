package com.newsfeed.domain.auth.controller;

import com.newsfeed.common.exception.ErrorCode;
import com.newsfeed.common.response.GlobalResponse;
import com.newsfeed.domain.auth.dto.LoginRequest;
import com.newsfeed.domain.auth.dto.SignUpRequest;
import com.newsfeed.domain.auth.dto.TokenResponse;
import com.newsfeed.domain.auth.security.PrincipalDetails;
import com.newsfeed.domain.auth.service.AuthService;
import com.newsfeed.domain.user.entity.User;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public GlobalResponse<Void> signUp(@RequestBody SignUpRequest request) {
//        System.out.println(request.getCellPhoneNumber());
        authService.signUp(request);
        return GlobalResponse.success(200, "회원가입이 완료되었습니다.", null);
    }

    @PostMapping("/login")
    public GlobalResponse<TokenResponse> login(@RequestBody LoginRequest request, HttpServletResponse response) {

        TokenResponse token = authService.login(request);
        // 토큰 만든 뒤 바디, 헤더에 토큰을 안넣어주고있다. 클라이언트가 모른다.
        // 헤더에 authorization키 안에 토큰을 뽑아와서 user dtl로 바꾸고 user로 바꾸는 작업을 해야한다.
        // userId만 토큰으로 만들어서 주는게 아니고 userId를 가지고있는 user을 전해주는 방식도 있다(비추천)
        // 어노테이션을 통해서 security에서 뽑아오는 방법
        // 클래스를 직접 써서 가져오는 방법

        // 클라이언트에 전달해줄 토큰 헤더에 담기
        response.setHeader("Authorization", "Bearer " + token.getAccessToken());

        return GlobalResponse.success(200, "로그인이 완료되었습니다.", null);
    }

    @DeleteMapping("/logout")
    public GlobalResponse<Void> logout() {
        // 서버는 JWT를 저장하지 않으므로 특별한 처리는 없다.
        // 클라이언트가 토큰을 버리면 로그아웃이 완료된다 ??
        // 1. 클라이언트가 토큰을 잊어버리게 하는거
        // 2. 토큰을 invalid 시키는 것을 해야한다.
        // security에 토큰정보가 들어가있는데 그것을 없애주고 이 토큰을 invalid 시켜서 못쓰는 토큰으로 만들어야한다.
        // security가 context에 넣어서 토큰이 유효하지 않다는 것을 인지시켜야하고 invalid 시켜야 한다.
        // blacklist 알아보기 -> 지금 당장은 그렇게 유효하지 않다.
        return GlobalResponse.successNodata(200, "로그아웃 완료");
    }

    // 실행 예시 코드
    // 1. api의 매개변수에 @AuthenticationPrincipal PrincipalDetails principalDetails 를 넣는다.
    // 2. 그러면 Spring Security 저장소에서 PrincipalDetails 객체에 User 객체를 담아서 넘겨준다.
    // 3. 그러면 principalDetails 객체 안에있는 getter로 유저 정보를 갖다 쓰면 된다.
    @GetMapping("/me")
    public GlobalResponse<User> getMe(@AuthenticationPrincipal PrincipalDetails principalDetails) {

        User user = principalDetails.getUser();
        System.out.println(user);
        System.out.println(user.getId());
        System.out.println(user.getEmail());
        System.out.println(user.getCellPhoneNumber());

        return GlobalResponse.success(200, "유저 정보", user);
    }

}
