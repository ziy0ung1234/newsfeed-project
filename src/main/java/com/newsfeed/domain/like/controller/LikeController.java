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
    @PostMapping("/newsfeeds/{newsfeedId}/likes")
    public ResponseEntity<GlobalResponse<Void>> addNewsfeedLike(@PathVariable Long newsfeedId){
        likeService.addNewsfeedLike(newsfeedId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
