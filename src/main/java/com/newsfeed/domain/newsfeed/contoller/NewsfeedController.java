package com.newsfeed.domain.newsfeed.contoller;

import com.newsfeed.domain.newsfeed.dto.NewsfeedRequest;
import com.newsfeed.domain.newsfeed.dto.NewsfeedResponse;
import com.newsfeed.domain.newsfeed.service.NewsfeedService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/newsfeeds")
public class NewsfeedController {

    private final NewsfeedService newsfeedService;

    @PostMapping("/{userId}")
    public ResponseEntity<NewsfeedResponse> createNewsfeed(
            @RequestBody NewsfeedRequest request,
            @PathVariable Long userId) {
        return ResponseEntity.status(HttpStatus.CREATED).body(newsfeedService.saveNewsfeed(request, userId));
    }

    @GetMapping("/{userId}/me")
    public ResponseEntity<List<NewsfeedResponse>> getNewsfeed(@PathVariable Long userId) {
        return ResponseEntity.status(HttpStatus.OK).body(newsfeedService.myNewsfeed(userId));
    }
}
