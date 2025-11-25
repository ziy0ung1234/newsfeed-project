package com.newsfeed.domain.like.service;

import com.newsfeed.common.exception.ErrorCode;
import com.newsfeed.common.exception.UnauthorizedException;
import com.newsfeed.domain.like.entity.Like;
import com.newsfeed.domain.like.repository.LikeRepository;
import com.newsfeed.domain.newsfeed.entity.Newsfeed;
import com.newsfeed.domain.newsfeed.repository.NewsfeedRepository;
import com.newsfeed.domain.user.entity.User;
import com.newsfeed.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class LikeService {
    private final LikeRepository likeRepository;
    private final UserRepository userRepository;
    private final NewsfeedRepository newsfeedRepository;

    public void addNewsfeedLike(Long newsfeedId, Long userId){
        User user = userRepository.findOrThrow(userId);
        Newsfeed newsfeed = newsfeedRepository.findOrThrow(newsfeedId);

        Like like = new Like(
                user,
                newsfeed,
                null
        );
        likeRepository.save(like);
    }

    public void cancelNewsfeedLike(Long newsfeedId, Long likeId, Long userId) {
        Newsfeed newsfeed = newsfeedRepository.findOrThrow(newsfeedId);
        Like like = likeRepository.findOrThrow(likeId);
        User user = userRepository.findOrThrow(userId);

        // like가 요청한 newsfeedid에 속하는지 검증
        if(!like.getNewsfeed().getId().equals(newsfeed.getId())) {
            throw new UnauthorizedException(ErrorCode.FORBIDDEN);
        }
        //현재 로그인 한 userid와 like.user.id가 같은지
        if(!like.getUser().getId().equals(user.getId())) {
            throw new UnauthorizedException(ErrorCode.FORBIDDEN);
        }
        likeRepository.deleteById(likeId);
    }
}
