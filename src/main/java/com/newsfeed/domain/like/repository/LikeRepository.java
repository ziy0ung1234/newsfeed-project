package com.newsfeed.domain.like.repository;

import com.newsfeed.common.exception.ErrorCode;
import com.newsfeed.common.exception.NotFoundException;
import com.newsfeed.domain.like.entity.Like;
import com.newsfeed.domain.newsfeed.entity.Newsfeed;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like, Long> {
    default Like findOrThrow(Long likeId) {
        return findById(likeId)
                .orElseThrow(()-> new NotFoundException(ErrorCode.LIKE_NOT_FOUND));
    }
}
