package com.newsfeed.domain.user.repository;

import com.newsfeed.common.exception.ErrorCode;
import com.newsfeed.common.exception.NotFoundException;
import com.newsfeed.domain.user.entity.User;
import jakarta.validation.constraints.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    default User findOrThrow(Long userId) {
        return findById(userId)
                .orElseThrow(()-> new NotFoundException(ErrorCode.USER_NOT_FOUND));
    }

    boolean existsByEmail(String email);
    boolean existsByCellPhoneNumber(String phone);
    Optional<User> findByEmail(String email);

    User findByUsername(String userName);
}
