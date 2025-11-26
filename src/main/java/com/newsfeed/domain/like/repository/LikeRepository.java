package com.newsfeed.domain.like.repository;

import com.newsfeed.common.exception.ErrorCode;
import com.newsfeed.common.exception.NotFoundException;
import com.newsfeed.domain.like.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Like 엔티티의 조회 및 저장을 담당하는 저장소입니다.
 * 기본 CRUD 기능과 함께 존재하지 않는 경우 예외를 던지는 헬퍼 메서드를 제공합니다.
 */

public interface LikeRepository extends JpaRepository<Like, Long> {
    default Like findOrThrow(Long likeId) {
        return findById(likeId)
                .orElseThrow(()-> new NotFoundException(ErrorCode.LIKE_NOT_FOUND));
    }
    Long countByNewsfeed_Id(Long newsfeedId);
    Long countByComment_Id(Long commentId);
}
