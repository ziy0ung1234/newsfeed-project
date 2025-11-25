package com.newsfeed.domain.comment;

import com.newsfeed.domain.comment.dto.*;
import com.newsfeed.common.response.GlobalResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class Controller {
    private final Service service;
    @PostMapping({
            "/newsfeeds/{newsfeedId}/comments",
            "/newsfeeds/{newsfeedId}/comments/{commentId}"
    })
    public ResponseEntity<GlobalResponse<CommentCreateRes>> postApi(@PathVariable Long newsfeedId, @PathVariable(required = false) Long commentId, @RequestBody CommentCreateReq req) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(HttpStatus.CREATED.value(),"postResponse",req,newsfeedId,commentId));
    }
    @GetMapping("/comments")
    public ResponseEntity<GlobalResponse<List<CommentWithChildrenResponse>>> getApi() {
        return ResponseEntity.status(HttpStatus.OK).body(service.find(HttpStatus.CREATED.value(),"getResponse"));
    }

    @GetMapping("/comments/{commentId}")
    public ResponseEntity<GlobalResponse<CommentWithChildrenResponse>> getDetailApi(@PathVariable Long commentId) {
        return ResponseEntity.status(HttpStatus.OK).body(service.findDetail(HttpStatus.CREATED.value(),"getResponse", commentId));
    }

    @PutMapping("/comments/{commentId}")
    public ResponseEntity<GlobalResponse<CommentPutResponse>> putApi(@PathVariable Long commentId,@RequestBody CommentPutRequest req) {
        return ResponseEntity.status(HttpStatus.OK).body(service.update(HttpStatus.OK.value(),"updateResponse", commentId, req));
    }

    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<GlobalResponse<Void>> deleteApi(@PathVariable Long commentId) {
        return ResponseEntity.status(HttpStatus.OK).body(service.delete(HttpStatus.NO_CONTENT.value(),"deleteResponse",commentId));
    }


}