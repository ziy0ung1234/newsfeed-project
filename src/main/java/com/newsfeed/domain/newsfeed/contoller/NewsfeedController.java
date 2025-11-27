package com.newsfeed.domain.newsfeed.contoller;

import com.newsfeed.common.response.GlobalResponse;
import com.newsfeed.domain.auth.security.PrincipalDetails;
import com.newsfeed.domain.newsfeed.dto.NewsfeedRequest;
import com.newsfeed.domain.newsfeed.dto.NewsfeedResponse;
import com.newsfeed.domain.newsfeed.service.NewsfeedService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/newsfeeds")
public class NewsfeedController {

    private final NewsfeedService newsfeedService;

    @PostMapping
    public ResponseEntity<GlobalResponse<NewsfeedResponse>> createNewsfeed(
            @Valid @RequestBody NewsfeedRequest request,
            @AuthenticationPrincipal PrincipalDetails principalDetails) {
        return ResponseEntity.status(HttpStatus.CREATED).body(GlobalResponse.success(201,
                "뉴스피드 생성완료",newsfeedService.saveNewsfeed(request, principalDetails)));
    }

    @GetMapping("/me")
    public ResponseEntity<GlobalResponse<List<NewsfeedResponse>>> getNewsfeed(@AuthenticationPrincipal PrincipalDetails principalDetails) {
        return ResponseEntity.status(HttpStatus.OK).body(GlobalResponse.success(200
                ,"마이 뉴스피드 조회완료",newsfeedService.myNewsfeed(principalDetails)));
    }

    @PutMapping("/{newsfeedId}")
    public ResponseEntity<GlobalResponse<NewsfeedResponse>> updateNewsfeed(
            @Valid @RequestBody NewsfeedRequest request,
            @PathVariable Long newsfeedId,
            @AuthenticationPrincipal PrincipalDetails principalDetails) {
        return ResponseEntity.status(HttpStatus.OK).body(GlobalResponse.success(200
                ,"뉴스피드 수정완료",newsfeedService.updateNewsfeed(request,newsfeedId,principalDetails)));
    }

    @DeleteMapping("/{newsfeedId}")
    public ResponseEntity<Void> deleteNewsfeed(@PathVariable Long newsfeedId, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        newsfeedService.deleteNewsfeed(newsfeedId, principalDetails);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
