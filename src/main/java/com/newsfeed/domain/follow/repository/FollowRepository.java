package com.newsfeed.domain.follow.repository;

import com.newsfeed.domain.follow.entity.Follow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FollowRepository extends JpaRepository<Follow, Long> {
    Optional<Follow> findByFollowerIdAndFollowingId(Long userId, Long otherId);

    // 내가 팔로우한 사람 수 (나 → 다른 사람들)
    Long countByFollowerId(Long userId);

    // 나를 팔로우한 사람 수 (다른 사람들 → 나)
    Long countByFollowingId(Long userId);


    // 내가 팔로우한 사람들 목록
    List<Follow> findAllByFollowerId(Long userId);

    // 나를 팔로우한 사람들 목록
    List<Follow> findAllByFollowingId(Long userId);
}
