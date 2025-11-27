package com.newsfeed.domain.newsfeed.service;

import com.newsfeed.common.exception.NotFoundException;
import com.newsfeed.common.response.GlobalResponse;
import com.newsfeed.domain.auth.security.PrincipalDetails;
import com.newsfeed.common.response.GlobalResponse;
import com.newsfeed.domain.newsfeed.dto.NewsfeedRequest;
import com.newsfeed.domain.newsfeed.dto.NewsfeedResponse;
import com.newsfeed.domain.newsfeed.entity.Newsfeed;
import com.newsfeed.domain.newsfeed.repository.NewsfeedRepository;
import com.newsfeed.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;
import static com.newsfeed.common.exception.ErrorCode.NEWSFEED_NOT_FOUND;



@Service
@RequiredArgsConstructor
public class NewsfeedService {

    private final NewsfeedRepository newsfeedRepository;


    @Transactional
    public NewsfeedResponse saveNewsfeed(NewsfeedRequest request, PrincipalDetails principalDetails) {

        // 인증 된 유저 정보
        User user = principalDetails.getUser();

        // 유저 정보와 뉴스피드 제목, 내용 요청 객체생성
        Newsfeed newsfeed = new Newsfeed(
                request.getTitle(),
                request.getContent(),
                user
        );

        // 뉴스피드 저장
        Newsfeed saveNewsfeed = newsfeedRepository.save(newsfeed);

        // 뉴스피드 응답객체 생성 후 반환
        return new NewsfeedResponse(
                saveNewsfeed.getId(),
                saveNewsfeed.getTitle(),
                saveNewsfeed.getContent(),
                saveNewsfeed.getCreatedAt(),
                saveNewsfeed.getModifiedAt()
        );
    }

    @Transactional(readOnly = true)
    public List<NewsfeedResponse> myNewsfeed(PrincipalDetails principalDetails) {

        // 로그인 된 유저 정보
        User user = principalDetails.getUser();

        // 로그인 된 유저의 뉴스피드가 존재하지 않을 경우
        List<Newsfeed> myNewsfeeds = newsfeedRepository.findAllByUser(user).orElseThrow(
                () -> new NotFoundException(NEWSFEED_NOT_FOUND)
        );

        List<NewsfeedResponse> myNewsfeedList = new ArrayList<>();

        // 순차 접근 후 뉴스피드 응답객체 생성
        for (Newsfeed newsfeed : myNewsfeeds) {
            NewsfeedResponse newsfeedResponse = new NewsfeedResponse(
                    newsfeed.getId(),
                    newsfeed.getTitle(),
                    newsfeed.getContent(),
                    newsfeed.getCreatedAt(),
                    newsfeed.getModifiedAt()
            );
            // 응답 객체 목록들을 마이뉴스피드 목록에 추가
            myNewsfeedList.add(newsfeedResponse);
        }
        // 성공 상태코드와 마이 뉴스피드 목록 반환
        return myNewsfeedList;
    }

    @Transactional
    public NewsfeedResponse updateNewsfeed(NewsfeedRequest request, Long newsfeedId, PrincipalDetails principalDetails) {
        // 로그인 된 유저 정보
        User user = principalDetails.getUser();

        // 로그인 된 유저의 뉴스피드가 존재하지 않을 경우
        Newsfeed newsfeed = newsfeedRepository.findByUserAndId(user, newsfeedId).orElseThrow(
                ()-> new NotFoundException(NEWSFEED_NOT_FOUND)
        );


        // 유저의 뉴스피드 수정
        newsfeed.update(request.getTitle(), request.getContent());

        // 수정된 뉴스피드 객체 생성 후 반환
        return new NewsfeedResponse(
                newsfeed.getId(),
                newsfeed.getTitle(),
                newsfeed.getContent(),
                newsfeed.getCreatedAt(),
                newsfeed.getModifiedAt()
        );
    }

    @Transactional
    public void deleteNewsfeed(Long newsfeedId, PrincipalDetails principalDetails) {

        User user = principalDetails.getUser();

        // 뉴스피드 존재 유무
        boolean newsfeed = newsfeedRepository.existsByUserAndId(user, newsfeedId);

        // 뉴스피드가 존재하지 않을경우
        if (!newsfeed) {
            throw new NotFoundException(NEWSFEED_NOT_FOUND);
        }
        // 뉴스피드 삭제
        newsfeedRepository.deleteById(newsfeedId);
    }

    @Transactional(readOnly = true)
    public GlobalResponse<?> search(int status, String message, String userName) {
//        newsfeedRepository.findB

        return null;
    }
}
