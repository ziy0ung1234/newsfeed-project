package com.newsfeed.domain.like.service;

import com.newsfeed.common.exception.ErrorCode;
import com.newsfeed.common.exception.UnauthorizedException;
import com.newsfeed.domain.comment.entity.Comment;
import com.newsfeed.domain.comment.repository.CommentRepository;
import com.newsfeed.domain.like.entity.Like;
import com.newsfeed.domain.like.repository.LikeRepository;
import com.newsfeed.domain.newsfeed.entity.Newsfeed;
import com.newsfeed.domain.newsfeed.repository.NewsfeedRepository;
import com.newsfeed.domain.user.entity.User;
import com.newsfeed.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 뉴스피드와 댓글에 대한 ‘좋아요’ 등록/취소 기능을 제공하는 서비스입니다.
 * 요청한 사용자와 리소스의 소유 관계를 검증하여 무단 접근을 방지합니다.
 * User, Newsfeed, Comment 엔티티를 조회해 Like 엔티티를 생성·삭제합니다.
 */
@Service
@RequiredArgsConstructor
@Transactional
public class LikeService {
    private final LikeRepository likeRepository;
    private final UserRepository userRepository;
    private final NewsfeedRepository newsfeedRepository;
    private final CommentRepository commentRepository;

    public void addNewsfeedLike(Long newsfeedId, Long userId){
        User user = userRepository.findOrThrow(userId);
        Newsfeed newsfeed = newsfeedRepository.findOrThrow(newsfeedId);

        Like like = new Like(
                user,
                newsfeed,
                null
        );
        likeRepository.save(like);
    }

    public void cancelNewsfeedLike(Long newsfeedId, Long likeId, Long userId) {
        Newsfeed newsfeed = newsfeedRepository.findOrThrow(newsfeedId);
        Like like = likeRepository.findOrThrow(likeId);
        User user = userRepository.findOrThrow(userId);

        // like가 요청한 newsfeedid에 속하는지 검증
        if(!like.getNewsfeed().getId().equals(newsfeed.getId())) {
            throw new UnauthorizedException(ErrorCode.FORBIDDEN);
        }
        //현재 로그인 한 userid와 like.user.id가 같은지
        if(!like.getUser().getId().equals(user.getId())) {
            throw new UnauthorizedException(ErrorCode.FORBIDDEN);
        }
        likeRepository.deleteById(likeId);
    }

    public void addCommentLike(Long commentId, Long userId) {
        User user = userRepository.findOrThrow(userId);
        Comment comment = commentRepository.findOrThrow(commentId);
        Like like = new Like(
                user,
                null,
                comment
        );
        likeRepository.save(like);
    }

    public void cancelCommentLike(Long commentId, Long likeId, Long userId) {
        Comment comment = commentRepository.findOrThrow(commentId);
        Like like = likeRepository.findOrThrow(likeId);
        User user = userRepository.findOrThrow(userId);

        // like가 요청한 commentid에 속하는지 검증
        if(!like.getComment().getId().equals(comment.getId())) {
            throw new UnauthorizedException(ErrorCode.FORBIDDEN);
        }
        //현재 로그인 한 userid와 like.user.id가 같은지
        if(!like.getUser().getId().equals(user.getId())) {
            throw new UnauthorizedException(ErrorCode.FORBIDDEN);
        }
        likeRepository.deleteById(likeId);
    }
}
