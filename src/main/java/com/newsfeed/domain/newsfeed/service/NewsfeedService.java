package com.newsfeed.domain.newsfeed.service;

import com.newsfeed.common.exception.ErrorCode;
import com.newsfeed.common.exception.NotFoundException;
import com.newsfeed.common.response.GlobalResponse;
import com.newsfeed.domain.auth.security.PrincipalDetails;
import com.newsfeed.common.response.GlobalResponse;
import com.newsfeed.domain.like.entity.Like;
import com.newsfeed.domain.like.repository.LikeRepository;
import com.newsfeed.domain.newsfeed.dto.NewsfeedLikeResponse;
import com.newsfeed.domain.newsfeed.dto.NewsfeedRequest;
import com.newsfeed.domain.newsfeed.dto.NewsfeedResponse;
import com.newsfeed.domain.newsfeed.entity.Newsfeed;
import com.newsfeed.domain.newsfeed.repository.NewsfeedRepository;
import com.newsfeed.domain.user.entity.User;
import com.newsfeed.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import static com.newsfeed.common.exception.ErrorCode.NEWSFEED_NOT_FOUND;



@Service
@RequiredArgsConstructor
public class NewsfeedService {

    private final NewsfeedRepository newsfeedRepository;
    private final LikeRepository likeRepository;
    private final UserRepository userRepository;


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
    public GlobalResponse<List<NewsfeedResponse>> searchByUserName(
            int status,
            String message,
            String userName,
            PrincipalDetails principalDetails) {
        User user = principalDetails.getUser();
        User searchUser = userRepository.findByUsername(userName);
        List<Newsfeed> lists = newsfeedRepository.findAllByUser(searchUser).orElseThrow(()-> new NotFoundException(ErrorCode.USER_NOT_FOUND));

            List<NewsfeedResponse> data = new ArrayList<>();
        for (Newsfeed list : lists) {
            data.add(new NewsfeedResponse(list.getId(),list.getTitle(),list.getContent(),list.getCreatedAt(),list.getModifiedAt()));
        }

        return GlobalResponse.success(status, message, data);
    }

    @Transactional(readOnly = true)
    public GlobalResponse<List<NewsfeedResponse>> searchAllNewsfeed(
            int status,
            String message,
            PrincipalDetails principalDetails,
            int page) {
        int constSize = 10;
        //0~10만 나오게 페이지네이션
        Pageable pageable = PageRequest.of(page, constSize, Sort.by(Sort.Direction.DESC, "createdAt"));
        Page<Newsfeed> lists = newsfeedRepository.findAll(pageable);
        List<NewsfeedResponse> data = new ArrayList<>();
        for (Newsfeed list : lists) {
            data.add(new NewsfeedResponse(list.getId(),list.getTitle(),list.getContent(),list.getCreatedAt(),list.getModifiedAt()));
        }
        return GlobalResponse.success(status, message, data);
    }

    public GlobalResponse<List<NewsfeedLikeResponse>> searchByLikesNewsfeed(
            int status,
            String message,
            PrincipalDetails principalDetails,
            int page) {
        int constSize = 5;
        //0~10만 나오게 페이지네이션
        Pageable pageable = PageRequest.of(page, constSize, Sort.by(Sort.Direction.DESC, "createdAt"));
        //페이지에서 전부 찾음
        Page<Newsfeed> lists = newsfeedRepository.findAll(pageable);
        //Page => List 형변환
        List<Newsfeed> collector = new ArrayList<>();
        //List로 다시 담음
        for (Newsfeed list : lists) {
            collector.add(list);
        }
        //GlobalResponse 에 담을 list 생성
        List<NewsfeedLikeResponse> data = new ArrayList<>();

        //종아요 수 담은 DTO 생성
        for (Newsfeed list : collector) {
            Long likeCount = likeRepository.countByNewsfeed_Id(list.getId());
            data.add(new NewsfeedLikeResponse(list, likeCount));
        }
        //data 좋아요순 sort
        data.sort((a,b)-> Long.compare(b.getLikeCount(), a.getLikeCount()));
        return GlobalResponse.success(status, message, data);
    }

    public GlobalResponse<List<NewsfeedResponse>> searchByDate(int status, String message, String startDate, String endDate) {

        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);

        LocalDateTime startDateTime = start.atStartOfDay();
        LocalDateTime endDateTime = end.atTime(23, 59, 59);

        List<Newsfeed> lists = newsfeedRepository.findAllByCreatedAtBetween(startDateTime, endDateTime);
        List<NewsfeedResponse> data = lists.stream()
                .map(n ->
                        new NewsfeedResponse(
                                n.getId(),
                                n.getTitle(),
                                n.getContent(),
                                n.getCreatedAt(),
                                n.getModifiedAt()))
                .toList();


        return GlobalResponse.success(status, message, data);
    }
}
