package com.newsfeed.domain.user.service;

import com.newsfeed.common.exception.NotFoundException;
import com.newsfeed.common.exception.UserNotFoundException;
import com.newsfeed.domain.user.dto.updateDto.UpdateRequest;
import com.newsfeed.domain.user.dto.updateDto.UpdateResponse;
import com.newsfeed.domain.user.dto.updatePasswordDto.UpdatePasswordRequest;
import com.newsfeed.domain.user.dto.updatePasswordDto.UpdatePasswordResponse;
import com.newsfeed.domain.user.dto.userInfoDto.UserInfoResponse;
import com.newsfeed.domain.user.entity.User;
import com.newsfeed.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.newsfeed.common.exception.ErrorCode.*;


/**
 * UserService
 * - 유저 정보 조회, 수정, 비밀번호 변경, 탈퇴 등
 * - 사용자 관련 핵심 비즈니스 로직을 담당하는 서비스 레이어
 */
@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * 유저 프로필 조회 (본인/타인 구분)
     */
    public UserInfoResponse userInfo(Long userId, Long targetId) {
        if (userId == null) throw new UserNotFoundException(LOGIN_REQUIRED);
        User user = userRepository.findById(targetId)
                .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND));

        boolean isMe = userId.equals(targetId);

        if (isMe) {
            return UserInfoResponse.forMyInfo(user);
        } else {
            return UserInfoResponse.forOtherInfo(user);
        }
    }


    /**
     * 내 정보 수정(이메일,유저이름,휴대폰번호 수정가능)
     */
    public UpdateResponse update(Long userId, UpdateRequest upRequest) {
        if (userId == null) throw new UserNotFoundException(LOGIN_REQUIRED);

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND));

        if (upRequest.getEmail() != null && !upRequest.getEmail().isBlank()) {
            user.setEmail(upRequest.getEmail());
        }
        if (upRequest.getUsername() != null && !upRequest.getUsername().isBlank()) {
            user.setUsername(upRequest.getUsername());
        }
        if (upRequest.getCellphone() != null && !upRequest.getCellphone().isBlank()) {
            user.setCellPhoneNumber(upRequest.getCellphone());
        }
        return UpdateResponse.of(user);
    }


    /**
     * 비밀번호 변경
     */
    public UpdatePasswordResponse updatePassword(Long userId, UpdatePasswordRequest pwRequest) {
        if (userId == null) throw new UserNotFoundException(LOGIN_REQUIRED);
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND));
        if (!passwordEncoder.matches(pwRequest.getCurrentPassword(), user.getPassword())) {
            throw new NotFoundException(PASSWORD_NOT_MATCH);
        }
        if (pwRequest.getNewPassword().equals(pwRequest.getCurrentPassword())) {
            throw new NotFoundException(INVALID_PASSWORD);
        }

        user.setPassword(passwordEncoder.encode(pwRequest.getNewPassword()));
        return UpdatePasswordResponse.of(user);
    }

    /**
     * 회원 탈퇴
     */
    public void delete(Long userId) {
        if (userId == null) throw new UserNotFoundException(LOGIN_REQUIRED);

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND));

        userRepository.delete(user);
    }

}
