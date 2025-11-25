package com.newsfeed.domain.user.repository;

import com.newsfeed.common.exception.ErrorCode;
import com.newsfeed.common.exception.NotFoundException;
import com.newsfeed.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    default User findOrThrow(Long userId) {
        return findById(userId)
                .orElseThrow(()-> new NotFoundException(ErrorCode.USER_NOT_FOUND));
    }
}
