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

import static com.newsfeed.common.exception.ErrorCode.NEWSFEED_NOT_FOUND;
import static com.newsfeed.common.exception.ErrorCode.USER_NOT_FOUND;


@Service
@RequiredArgsConstructor
public class NewsfeedService {

    private final NewsfeedRepository newsfeedRepository;
    private final UserRepository userRepository;

    @Transactional
    public NewsfeedResponse saveNewsfeed(NewsfeedRequest request, Long userId) {

        // 유저아이디 검증 후 유저 데이터 조회
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
                saveNewsfeed.getModifiedAt()
        );
    }

    @Transactional(readOnly = true)
    public List<NewsfeedResponse> myNewsfeed(Long userId) {

        // 뉴스피드 유저아이디 검증 후 해당 유저아이디의 뉴스피드 목록 조회
        List<Newsfeed> myNewsfeeds = newsfeedRepository.findByUserId(userId);

        List<NewsfeedResponse> myNewsfeedList = new ArrayList<>();

        // 순차 접근 후 뉴스피드 응답
        for(Newsfeed newsfeed : myNewsfeeds) {
            NewsfeedResponse newsfeedResponse = new NewsfeedResponse(
                    newsfeed.getId(),
                    newsfeed.getTitle(),
                    newsfeed.getContent(),
                    newsfeed.getCreatedAt(),
                    newsfeed.getModifiedAt()
            );
            // 리스트에 응답한 뉴스피드 객체들을 저장
            myNewsfeedList.add(newsfeedResponse);
        }
        return myNewsfeedList;
    }

    @Transactional
    public NewsfeedResponse updateNewsfeed(NewsfeedRequest request, Long newsfeedId){
        // 수정할 뉴스피드 아이디 데이터 검증 후 조회
        Newsfeed newsfeed = newsfeedRepository.findById(newsfeedId).orElseThrow(
                () -> new NotFoundException(NEWSFEED_NOT_FOUND)
        );
        // 뉴스피드 업데이트 요청
        newsfeed.update(request.getTitle(),request.getContent());

        return new NewsfeedResponse(
                newsfeed.getId(),
                newsfeed.getTitle(),
                newsfeed.getContent(),
                newsfeed.getCreatedAt(),
                newsfeed.getModifiedAt()
        );
    }

    @Transactional
    public void deleteNewsfeed(Long newsfeedId) {
        // 뉴스피드 존재 유무
        boolean newsfeed = newsfeedRepository.existsById(newsfeedId);

        // 뉴스피드가 존재하지 않을경우
        if(!newsfeed) {
            throw new NotFoundException(NEWSFEED_NOT_FOUND);
        }
        // 뉴스피드 삭제
        newsfeedRepository.deleteById(newsfeedId);
    }
}
