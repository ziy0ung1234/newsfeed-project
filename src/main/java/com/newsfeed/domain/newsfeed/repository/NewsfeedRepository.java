package com.newsfeed.domain.newsfeed.repository;

import com.newsfeed.common.exception.ErrorCode;
import com.newsfeed.common.exception.NotFoundException;
import com.newsfeed.domain.newsfeed.entity.Newsfeed;
import com.newsfeed.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsfeedRepository extends JpaRepository<Newsfeed, Long> {
    default Newsfeed findOrThrow(Long newsfeedId) {
        return findById(newsfeedId)
                .orElseThrow(()-> new NotFoundException(ErrorCode.NEWSFEED_NOT_FOUND));
    }
}
