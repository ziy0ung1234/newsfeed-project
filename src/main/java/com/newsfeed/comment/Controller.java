package com.newsfeed.comment;

import com.newsfeed.comment.dto.CommentCreateReq;
import com.newsfeed.common.response.GlobalResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class Controller {
    private final Service service;
    @PostMapping({
            "/newsfeeds/{newsfeedId}/comments",
            "/newsfeeds/{newsfeedId}/comments/{parentCommentId}"
    })
    public ResponseEntity<GlobalResponse<?>> postApi(@PathVariable Long newsfeedId, @PathVariable(required = false) Long parentCommentId, @RequestBody CommentCreateReq req) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(HttpStatus.CREATED.value(),"postResponse",req,newsfeedId,parentCommentId));
    }
//    @GetMapping("/comments")
//    public ResponseEntity<GlobalResponse<?>> getApi() {
//        return ResponseEntity.status(HttpStatus.CREATED).body(service.find(HttpStatus.CREATED.value(),"getResponse"));
//    }

}