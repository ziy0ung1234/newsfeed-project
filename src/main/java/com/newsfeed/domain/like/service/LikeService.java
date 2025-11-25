package com.newsfeed.domain.like.service;

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

    private static final Long DUMMY_USER_ID = 10L;
    public void addNewsfeedLike(Long newsfeedId){
        User user = userRepository.findOrThrow(DUMMY_USER_ID);
        Newsfeed newsfeed = newsfeedRepository.findOrThrow(newsfeedId);

        Like like = new Like(
                user,
                newsfeed,
                null
        );

        likeRepository.save(like);
    }

}
