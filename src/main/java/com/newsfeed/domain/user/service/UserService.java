package com.newsfeed.domain.user.service;

import com.newsfeed.common.exception.PasswordException;
import com.newsfeed.common.exception.UserNotFoundException;
import com.newsfeed.domain.user.dto.deleteResponse.DeleteResponse;
import com.newsfeed.domain.user.dto.updateDto.UpdateRequest;
import com.newsfeed.domain.user.dto.updateDto.UpdateResponse;
import com.newsfeed.domain.user.dto.updatePasswordDto.UpdatePasswordRequest;
import com.newsfeed.domain.user.dto.updatePasswordDto.UpdatePasswordResponse;
import com.newsfeed.domain.user.dto.userInfoDto.UserInfoResponse;
import com.newsfeed.domain.user.entity.User;
import com.newsfeed.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.newsfeed.common.exception.ErrorCode.*;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;

    /**
     * 유저 프로필 조회
     * - targetId : 조회하고 싶은 유저
     * - userId : 로그인 유저
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
     * 내 정보 수정
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
     * 비밀번호 수정
     */
    public UpdatePasswordResponse updatePassword(Long userId, UpdatePasswordRequest pwRequest) {
        if (userId == null) throw new UserNotFoundException(LOGIN_REQUIRED);
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND));

        if (!user.getPassword().equals(pwRequest.getCurrentPassword())) {
            throw new PasswordException(USER_PASSWORD_NOT_FOUND);
        }

        if (pwRequest.getNwePassword().equals(pwRequest.getCurrentPassword())) {
            throw new PasswordException(PASSWORD_SAME);
        }

        user.setPassword(pwRequest.getNwePassword());
        return UpdatePasswordResponse.of(user);
    }


    /**
     * 회원 탈퇴
     */
    public DeleteResponse delete(Long userId) {
        if (userId == null) throw new UserNotFoundException(LOGIN_REQUIRED);

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND));

        userRepository.delete(user);
        return DeleteResponse.of(user.getId(),"회원 탈퇴 완료");
    }

}
