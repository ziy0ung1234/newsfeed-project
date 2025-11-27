package com.newsfeed.domain.like.repository;

import com.newsfeed.domain.like.entity.Like;
import com.newsfeed.domain.newsfeed.entity.Newsfeed;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikeRepository extends JpaRepository<Like, Long> {
    List<Like> findByNewsfeed(Newsfeed newsfeed);

    List<Like> findByNewsfeed_Id(List<Newsfeed> newsfeedId);
}
