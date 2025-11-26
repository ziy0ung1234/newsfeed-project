package com.newsfeed.domain.follow.service;

import com.newsfeed.common.exception.NotFoundException;
import com.newsfeed.common.exception.UserNotFoundException;
import com.newsfeed.domain.follow.entity.Follow;
import com.newsfeed.domain.follow.repository.FollowRepository;
import com.newsfeed.domain.user.entity.User;
import com.newsfeed.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.newsfeed.common.exception.ErrorCode.*;

@Service
@RequiredArgsConstructor
@Transactional
public class FollowService {

    private final FollowRepository followRepository;
    private final UserRepository userRepository;

    /**
    * 팔로우(내가 -> 다른사람)
    */
    public void follow(Long userId, Long otherId) {

        //로그인 계정 검증
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new UserNotFoundException(LOGIN_REQUIRED)
        );

        //상대방 계정 검증
        User other = userRepository.findById(otherId)
                .orElseThrow(()-> new UserNotFoundException(VALIDATION_ERROR)
        );

        //본인 팔로우 금지
        if(userId.equals(otherId)) {
            throw new NotFoundException(VALIDATION_ERROR);
        }

        //팔로우 중복 금지
        boolean overlapping = followRepository.findByFollowerIdAndFollowingId(userId,otherId).isPresent();
        if (overlapping) {
            throw new NotFoundException(VALIDATION_ERROR);
        }

        //내 PK값 내용을 상대방 PK로 넘기기
        followRepository.save(new Follow(userId,user,other));
    }

    /**
    * 언팔로우(친구 끊기)
    */
    public void unfollow(Long userId, Long otherId) {
       Follow relation = followRepository.findByFollowerIdAndFollowingId(userId,otherId)
               .orElseThrow(()-> new NotFoundException(VALIDATION_ERROR));
       followRepository.delete(relation);
    }

}
