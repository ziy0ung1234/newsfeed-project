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

import java.util.ArrayList;
import java.util.List;

import static com.newsfeed.common.exception.ErrorCode.USER_NOT_FOUND;


@Service
@RequiredArgsConstructor
public class NewsfeedService {

    private final NewsfeedRepository newsfeedRepository;
    private final UserRepository userRepository;

    @Transactional
    public NewsfeedResponse saveNewsfeed(NewsfeedRequest request, Long userId) {

        // 유저아이디 검증
        User user = userRepository.findById(userId).orElseThrow(
                () -> new NotFoundException(USER_NOT_FOUND)
        );

        // 유저아이디, 뉴스피드 제목과 내용 요청
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

    @Transactional(readOnly = true)
    public List<NewsfeedResponse> myNewsfeed(Long userId) {

        // 유저아이디 검증
        userRepository.findById(userId).orElseThrow(
                () -> new NotFoundException(USER_NOT_FOUND)
        );
        // 검증 된 유저아이디의 전체 뉴스피드
        List<Newsfeed> myNewsfeeds = newsfeedRepository.findAll();

        List<NewsfeedResponse> myNewsfeedList = new ArrayList<>();

        // 순차 접근 후 뉴스피드 응답
        for(Newsfeed newsfeed : myNewsfeeds) {
            NewsfeedResponse newsfeedResponse = new NewsfeedResponse(
                    newsfeed.getId(),
                    newsfeed.getTitle(),
                    newsfeed.getContent(),
                    newsfeed.getCreatedAt(),
                    newsfeed.getUpdatedAt()
            );
            // 리스트에 응답한 뉴스피드 객체들을 저장
            myNewsfeedList.add(newsfeedResponse);
        }
        return myNewsfeedList;
    }
}
