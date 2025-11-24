package com.newsfeed.common.repository;

import com.newsfeed.common.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like, Long> {
}
