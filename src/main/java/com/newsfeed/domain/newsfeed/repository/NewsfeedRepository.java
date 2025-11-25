package com.newsfeed.domain.newsfeed.repository;

import com.newsfeed.domain.newsfeed.entity.Newsfeed;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsfeedRepository extends JpaRepository<Newsfeed, Long> {
}
