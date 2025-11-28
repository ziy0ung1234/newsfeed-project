package com.newsfeed.domain.follow.service;

import com.newsfeed.common.exception.NotFoundException;
import com.newsfeed.common.exception.UserNotFoundException;
import com.newsfeed.domain.follow.dto.followCountResponse.FollowCountResponse;
import com.newsfeed.domain.follow.dto.followUserResponse.FollowUserResponse;
import com.newsfeed.domain.follow.dto.followingUserResponse.FollowingUserResponse;
import com.newsfeed.domain.follow.entity.Follow;
import com.newsfeed.domain.follow.repository.FollowRepository;
import com.newsfeed.domain.newsfeed.entity.Newsfeed;
import com.newsfeed.domain.newsfeed.repository.NewsfeedRepository;
import com.newsfeed.domain.user.entity.User;
import com.newsfeed.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.newsfeed.common.exception.ErrorCode.*;

/**
 * FollowService
 * - 유저 간 팔로우/언팔로우 로직
 * - 팔로워/팔로잉 목록 및 수 조회 처리
 */
@Service
@RequiredArgsConstructor
@Transactional
public class FollowService {

    private final FollowRepository followRepository;
    private final UserRepository userRepository;
    private final NewsfeedRepository newsfeedRepository;

    /**
     * 팔로우 (userId → otherId)
     * userId = 나
     * otherId = 내가 팔로우할 상대방
     */
    public void follow(User user, Long otherId) {
        Long userId = user.getId();

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
        followRepository.save(new Follow(user,other));
    }

    /**
     * 언팔로우
     * (userId → otherId 관계 삭제)
     */
    public void unfollow(Long userId, Long otherId) {

        //상대방 계정 검증
        User other = userRepository.findById(otherId)
                .orElseThrow(()-> new UserNotFoundException(VALIDATION_ERROR)
                );
        
       Follow relation = followRepository.findByFollowerIdAndFollowingId(userId,otherId)
               .orElseThrow(()-> new NotFoundException(VALIDATION_ERROR));
       followRepository.delete(relation);
    }

    /**
     * 팔로우/팔로워 수 조회
     * followingCount = 내가 팔로우한 사람 수
     * followerCount  = 나를 팔로우한 사람 수
     */
    public FollowCountResponse followers(Long userId) {
        Long follower = followRepository.countByFollowingId(userId);
        Long following = followRepository.countByFollowerId(userId);
        return FollowCountResponse.of(following, follower);
    }

    /**
     * 팔로잉 목록 조회
     * = 내가 팔로우하고 있는 유저 리스트
     * followerId = 나
     * followingId = 상대방
     */
    public List<FollowUserResponse> followingList(Long userId) {
        return followRepository.findAllByFollowerId(userId)
                .stream()
                .map(follow -> FollowUserResponse.of(follow.getFollowing()))
                .toList();
    }

    /**
     * 팔로워 목록 조회
     * = 나를 팔로우하고 있는 유저 리스트
     * followingId = 나
     * followerId = 상대방
     */
    public List<FollowUserResponse> followList(Long userId) {
        return followRepository.findAllByFollowingId(userId)
                .stream()
                .map(following -> FollowUserResponse.of(following.getFollower()))
                .toList();
    }

    /**
     * 팔로잉 유저 단건유저 게시물 조회
     */
    public Page<FollowingUserResponse> followingInfo(Long userId, Long otherId, int page, int size) {

        //상대방 계정 검증
        User other = userRepository.findById(otherId)
                .orElseThrow(()-> new UserNotFoundException(USER_NOT_FOUND));

        boolean isFollowing = followRepository
                .findByFollowerIdAndFollowingId(userId, otherId)
                .isPresent();

        if (!isFollowing) {
            throw new NotFoundException(FORBIDDEN); // 팔로우 안한 유저의 게시글 조회 금지
        }

        Pageable pageable = PageRequest.of(page, size);
        Page<Newsfeed> pageResult = newsfeedRepository
                .findAllByUserOrderByIdDesc(other, pageable);

        return pageResult.map(FollowingUserResponse::of);
    }
}
