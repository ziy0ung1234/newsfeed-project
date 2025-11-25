package com.newsfeed.domain.like.controller;

import com.newsfeed.common.response.GlobalResponse;
import com.newsfeed.domain.like.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
