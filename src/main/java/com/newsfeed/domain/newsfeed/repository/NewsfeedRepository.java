package com.newsfeed.domain.newsfeed.repository;

import com.newsfeed.common.exception.ErrorCode;
import com.newsfeed.common.exception.NotFoundException;
import com.newsfeed.domain.newsfeed.entity.Newsfeed;
import com.newsfeed.domain.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface NewsfeedRepository extends JpaRepository<Newsfeed, Long> {
    default Newsfeed findOrThrow(Long newsfeedId) {
        return findById(newsfeedId)
                .orElseThrow(()-> new NotFoundException(ErrorCode.NEWSFEED_NOT_FOUND));
    }
    // 유저의 뉴스피드 존재 유무
    boolean existsByUserAndId(User user, Long Id);
    // 유저의 뉴스피드 목록 조회하기
    Optional<List<Newsfeed>> findAllByUser(User user);
    // 유저의 뉴스피드 아이디로 조회
    Optional<Newsfeed> findByUserAndId(User user, Long newsfeedId);

    // 날짜 기간사이 조회
    List<Newsfeed> findAllByCreatedAtBetween(LocalDateTime start, LocalDateTime end);

    // 유저 기준, ID 내림차순, Pageable 적용
    Optional<Page<Newsfeed>> findAllByUserOrderByIdDesc(User user, Pageable pageable);
}
