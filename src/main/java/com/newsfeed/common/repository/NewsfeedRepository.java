package com.newsfeed.common.repository;

import com.newsfeed.common.entity.Newsfeed;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsfeedRepository extends JpaRepository<Newsfeed, Long> {
}
