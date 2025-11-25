package com.newsfeed.domain.like.controller;

import com.newsfeed.common.response.GlobalResponse;
import com.newsfeed.domain.like.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 뉴스피드와 댓글에 대한 좋아요/취소 기능을 제공하는 REST 컨트롤러입니다.
 * 인증이 정해지기 전까지 더미 사용자 ID로 좋아요 요청을 처리합니다.
 * 각 엔드포인트는 Service 레이어에 위임하며 성공 시 204(No Content)를 반환합니다.
 */

@RestController
@RequiredArgsConstructor
public class LikeController {
    private final LikeService likeService;

    private static final Long DUMMY_USER_ID = 10L;

    @PostMapping("/newsfeeds/{newsfeedId}/likes")
    public ResponseEntity<GlobalResponse<Void>> addNewsfeedLike(@PathVariable Long newsfeedId){
        likeService.addNewsfeedLike(newsfeedId, DUMMY_USER_ID);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    @DeleteMapping("/newsfeeds/{newsfeedId}/likes/{likeId}")
    public ResponseEntity<GlobalResponse<Void>> cancelNewsfeedLike(
            @PathVariable Long newsfeedId,
            @PathVariable Long likeId
    ){
        likeService.cancelNewsfeedLike(newsfeedId, likeId, DUMMY_USER_ID);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    @PostMapping("/comments/{commentId}/likes")
    public ResponseEntity<GlobalResponse<Void>> addCommentLike(@PathVariable Long commentId){
        likeService.addCommentLike(commentId, DUMMY_USER_ID);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    @DeleteMapping("/comments/{commentId}/likes/{likeId}")
    public ResponseEntity<GlobalResponse<Void>> cancelCommentLike(
            @PathVariable Long commentId,
            @PathVariable Long likeId
    ){
        likeService.cancelCommentLike(commentId, likeId, DUMMY_USER_ID);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
