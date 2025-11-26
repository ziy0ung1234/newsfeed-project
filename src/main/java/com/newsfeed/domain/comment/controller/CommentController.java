package com.newsfeed.domain.comment.controller;

import com.newsfeed.domain.auth.security.PrincipalDetails;
import com.newsfeed.domain.comment.service.CommentService;
import com.newsfeed.domain.comment.dto.*;
import com.newsfeed.common.response.GlobalResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentService service;
    @PostMapping({
            "/newsfeeds/{newsfeedId}/comments",
            "/newsfeeds/{newsfeedId}/comments/{commentId}"
    })
    public ResponseEntity<GlobalResponse<CommentCreateRes>> createComment(
            @PathVariable Long newsfeedId,
            @PathVariable(required = false) Long commentId,
            @RequestBody CommentCreateReq req,
            @AuthenticationPrincipal PrincipalDetails principalDetails
    ) {
        System.out.println(principalDetails);
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(HttpStatus.CREATED.value(),"postResponse",req,newsfeedId,commentId,principalDetails));
    }
    @GetMapping("newsfeeds/{newsfeedId}/comments")
    public ResponseEntity<GlobalResponse<List<CommentFindResponse>>> getFromNewsfeedsComments(@PathVariable Long newsfeedId) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getFromNewsfeedsComments(HttpStatus.OK.value(),"getResponse", newsfeedId));
    }

    @GetMapping("/comments/{commentId}")
    public ResponseEntity<GlobalResponse<List<CommentFindResponse>>> getFromCommentsChildren(@PathVariable Long commentId) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getFromCommentsChildren(HttpStatus.OK.value(),"getResponse", commentId));
    }

    @PutMapping("/comments/{commentId}")
    public ResponseEntity<GlobalResponse<CommentPutResponse>> updateComment(
            @PathVariable Long commentId,
            @RequestBody CommentPutRequest req,
            @AuthenticationPrincipal PrincipalDetails principalDetails) {
        return ResponseEntity.status(HttpStatus.OK).body(service.update(HttpStatus.OK.value(),"updateResponse", commentId, req, principalDetails));
    }

    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<GlobalResponse<Void>> deleteComment(
            @PathVariable Long commentId,
            PrincipalDetails principalDetails
            ) {
        return ResponseEntity.status(HttpStatus.OK).body(service.delete(HttpStatus.NO_CONTENT.value(),"deleteResponse",commentId, principalDetails));
    }


}