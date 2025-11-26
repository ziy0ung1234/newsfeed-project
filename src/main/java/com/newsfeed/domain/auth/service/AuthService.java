package com.newsfeed.domain.auth.service;

import com.newsfeed.common.exception.*;
import com.newsfeed.domain.auth.dto.LoginRequest;
import com.newsfeed.domain.auth.dto.SignUpRequest;
import com.newsfeed.domain.auth.dto.TokenResponse;
import com.newsfeed.domain.auth.jwt.JwtProvider;
import com.newsfeed.domain.user.entity.User;
import com.newsfeed.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;

    public void signUp(SignUpRequest request) {
        // 중복 이메일 체크
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new SignUpFailException(ErrorCode.USER_ALREADY_EXISTS);
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
        Long userId = jwtProvider.getUserId(token);

        return new TokenResponse(token);
    }
}
