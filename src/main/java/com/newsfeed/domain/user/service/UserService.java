package com.newsfeed.domain.user.service;

import com.newsfeed.common.exception.UserNotFoundException;
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

        // 조회 대상(target) 유저 찾기
         User user = userRepository.findById(targetId)
                 .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND));

            boolean isMe = userId.equals(targetId);

            if(isMe) {
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

        // 조회 대상(target) 유저 찾기
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND));

        // 수정 가능한 필드만 업데이트
        user.setEmail(upRequest.getEmail());
        user.setUsername(upRequest.getUsername());
        user.setCellPhoneNumber(upRequest.getCellPhoneNumber());

        return UpdateResponse.of(user);
    }

    /**
     * 회원 탈퇴
     */
    public DeleteResponse delete(Long userId) {

        if (userId == null) throw new UserNotFoundException(LOGIN_REQUIRED);

        // 조회 대상(target) 유저 찾기
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND));

        userRepository.delete(user);

        return DeleteResponse.of(user.getId(),"회원 탈퇴 완료");
    }

}
