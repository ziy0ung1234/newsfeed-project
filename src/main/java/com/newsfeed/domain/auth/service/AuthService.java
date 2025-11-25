package com.newsfeed.domain.auth.service;

import com.newsfeed.common.exception.ErrorCode;
import com.newsfeed.common.exception.LoginFailException;
import com.newsfeed.domain.auth.dto.LoginRequest;
import com.newsfeed.domain.auth.dto.SignUpRequest;
import com.newsfeed.domain.auth.dto.TokenResponse;
import com.newsfeed.domain.auth.jwt.JwtProvider;
import com.newsfeed.domain.user.entity.User;
import com.newsfeed.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;

    public void signUp(SignUpRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new IllegalArgumentException("이미 사용중인 이메일입니다.");
        }

        User user = new User(
                request.getUsername(),
                request.getEmail(),
                passwordEncoder.encode(request.getPassword()),
                request.getCellPhoneNumber()
        );

        userRepository.save(user);
    }

    public TokenResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new LoginFailException(ErrorCode.USER_NOT_MATCH));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new LoginFailException(ErrorCode.USER_NOT_MATCH);
        }

        String token = jwtProvider.generateToken(user.getId(), user.getEmail());
        System.out.println(token);
        Long userId = jwtProvider.getUserId(token);
        System.out.println(userId);
        return new TokenResponse(token);
    }
}
