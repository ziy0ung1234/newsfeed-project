package com.newsfeed.domain.user.repository;

import com.newsfeed.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
    boolean existsByCellPhoneNumber(String phone);
    Optional<User> findByEmail(String email);
}
