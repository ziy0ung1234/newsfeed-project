package com.newsfeed.domain.like.controller;

import com.newsfeed.common.response.GlobalResponse;
import com.newsfeed.domain.auth.security.PrincipalDetails;
import com.newsfeed.domain.like.dto.response.GetCountResponse;
import com.newsfeed.domain.like.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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


    @PostMapping("/newsfeeds/{newsfeedId}/likes")
    public ResponseEntity<GlobalResponse<Void>> addNewsfeedLike(
            @PathVariable Long newsfeedId,
            @AuthenticationPrincipal PrincipalDetails principalDetails
    ) {
        likeService.addNewsfeedLike(newsfeedId, principalDetails.getUser().getId());
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(GlobalResponse.successNodata(HttpStatus.CREATED.value(), "뉴스피드 좋아요 생성"));
    }

    @DeleteMapping("/newsfeeds/{newsfeedId}/likes/{likeId}")
    public ResponseEntity<GlobalResponse<Void>> cancelNewsfeedLike(
            @PathVariable Long newsfeedId,
            @PathVariable Long likeId,
            @AuthenticationPrincipal PrincipalDetails principalDetails
    ) {
        likeService.cancelNewsfeedLike(newsfeedId, likeId, principalDetails.getUser().getId());
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body(GlobalResponse.successNodata(HttpStatus.NO_CONTENT.value(), "뉴스피드 좋아요 취소"));
    }

    @PostMapping("/comments/{commentId}/likes")
    public ResponseEntity<GlobalResponse<Void>> addCommentLike(
            @PathVariable Long commentId,
            @AuthenticationPrincipal PrincipalDetails principalDetails
    ) {
        likeService.addCommentLike(commentId, principalDetails.getUser().getId());
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(GlobalResponse.successNodata(HttpStatus.CREATED.value(), "댓글 좋아요 생성"));
    }

    @DeleteMapping("/comments/{commentId}/likes/{likeId}")
    public ResponseEntity<GlobalResponse<Void>> cancelCommentLike(
            @PathVariable Long commentId,
            @PathVariable Long likeId,
            @AuthenticationPrincipal PrincipalDetails principalDetails
    ) {
        likeService.cancelCommentLike(commentId, likeId, principalDetails.getUser().getId());
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body(GlobalResponse.successNodata(HttpStatus.NO_CONTENT.value(), "댓글 좋아요 생성"));
    }

    @GetMapping("/newsfeeds/{newsfeedId}/like-counts")
    public ResponseEntity<GlobalResponse<GetCountResponse>> getNewsfeedLikeCounts(
            @PathVariable Long newsfeedId,
            @AuthenticationPrincipal PrincipalDetails principalDetails
    ) {
        GetCountResponse response = likeService.getNewsfeedCount(
                newsfeedId,
                principalDetails.getUser().getId()
        );
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(GlobalResponse.success(HttpStatus.OK.value(), "뉴스피드 좋아요 수 조회", response));
    }
    @GetMapping("/comments/{commentId}/like-counts")
    public ResponseEntity<GlobalResponse<GetCountResponse>> getCommentLikeCounts(
            @PathVariable Long commentId,
            @AuthenticationPrincipal PrincipalDetails principalDetails
    ) {
        GetCountResponse response = likeService.getCommentCount(
                commentId,
                principalDetails.getUser().getId()
        );
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(GlobalResponse.success(HttpStatus.OK.value(), "댓글 좋아요 수 조회", response));
    }
}