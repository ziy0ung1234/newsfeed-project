package com.newsfeed.domain.newsfeed.service;

import com.newsfeed.common.exception.NotFoundException;
import com.newsfeed.domain.newsfeed.dto.NewsfeedRequest;
import com.newsfeed.domain.newsfeed.dto.NewsfeedResponse;
import com.newsfeed.domain.newsfeed.entity.Newsfeed;
import com.newsfeed.domain.newsfeed.repository.NewsfeedRepository;
import com.newsfeed.domain.user.entity.User;
import com.newsfeed.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import static com.newsfeed.common.exception.ErrorCode.USER_NOT_FOUND;


@Service
@RequiredArgsConstructor
public class NewsfeedService {

    private final NewsfeedRepository newsfeedRepository;
    private final UserRepository userRepository;

    @Transactional
    public NewsfeedResponse saveNewsfeed(NewsfeedRequest request, Long userId) {

        User user = userRepository.findById(userId).orElseThrow(
                () -> new NotFoundException(USER_NOT_FOUND)
        );

        // 유저토큰 뉴스피드 제목, 내용 요청
        Newsfeed newsfeed = new Newsfeed(
                request.getTitle(),
                request.getContent(),
                user
        );

        // 뉴스피드 저장
        Newsfeed saveNewsfeed = newsfeedRepository.save(newsfeed);

        // 뉴스피드 응답
        return new NewsfeedResponse(
                saveNewsfeed.getId(),
                saveNewsfeed.getTitle(),
                saveNewsfeed.getContent(),
                saveNewsfeed.getCreatedAt(),
                saveNewsfeed.getUpdatedAt()
        );
    }
}
