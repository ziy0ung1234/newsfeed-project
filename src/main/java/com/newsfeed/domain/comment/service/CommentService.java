package com.newsfeed.domain.comment.service;

import com.newsfeed.domain.auth.security.PrincipalDetails;
import com.newsfeed.domain.comment.dto.*;
import com.newsfeed.domain.comment.entity.Comment;

import com.newsfeed.common.exception.ErrorCode;
import com.newsfeed.common.exception.NotFoundException;
import com.newsfeed.common.response.GlobalResponse;
import com.newsfeed.domain.comment.repository.CommentRepository;
import com.newsfeed.domain.newsfeed.entity.Newsfeed;
import com.newsfeed.domain.newsfeed.repository.NewsfeedRepository;
import com.newsfeed.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {
    private final CommentRepository commentRepository;
    private final NewsfeedRepository newsfeedRepository;

    //댓글 생성
    public GlobalResponse<CommentCreateRes> createComment(int status, String message, CommentCreateReq req, Long newsfeedId, PrincipalDetails principalDetails) {

        Newsfeed newsfeed = newsfeedRepository.findById(newsfeedId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.NEWSFEED_NOT_FOUND));

        User user = principalDetails.getUser();

        //생성시에 부모가 없는 새 댓글일 수 있음. null, depth = 0 으로 새로 계층이 형성되는 댓글시에는 해당 변수 사용
        int depth = 0;

        Comment comment = new Comment(
                req.getContent(),
                depth,
                user,
                newsfeed,
                null
        );
        commentRepository.save(comment);
        CommentCreateRes res = new CommentCreateRes(comment);
        return GlobalResponse.success(status, message, res);
    }

    public GlobalResponse<CommentCreateRes> createChildComment(int status, String message, CommentCreateReq req, Long newsfeedId, Long parentCommentId, PrincipalDetails principalDetails) {

        Newsfeed newsfeed = newsfeedRepository.findById(newsfeedId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.NEWSFEED_NOT_FOUND));

        User user = principalDetails.getUser();

        //생성시에 부모가 없는 새 댓글일 수 있음. null, depth = 0 으로 새로 계층이 형성되는 댓글시에는 해당 변수 사용
        Comment parentComment = null;
        int depth = 0;

        //부모댓글이 있는 경우. 상위 뎁스를 확인한 뒤 그 depth보다 +1 만큼만 더해줌.
        if (!(parentCommentId == null)) {
            parentComment = commentRepository.findById(parentCommentId)
                    .orElseThrow(() -> new NotFoundException(ErrorCode.COMMENT_NOT_FOUND));
            //대대댓글 이상의 기능 방지
            if (parentComment.getDepth() >= 1) {
                throw new NotFoundException(ErrorCode.USER_NOT_MATCH);
            }
            depth = parentComment.getDepth() + 1;
        }
        Comment comment = new Comment(
                req.getContent(),
                depth,
                user,
                newsfeed,
                parentComment
        );
        commentRepository.save(comment);
        CommentCreateRes res = new CommentCreateRes(comment);
        return GlobalResponse.success(status, message, res);

    }

    //게시글에 대한 부모계층 댓글 조회
    public GlobalResponse<List<CommentFindResponse>> getFromNewsfeedsComments(int status, String message, Long newsfeedId,PrincipalDetails principalDetails) {

            Newsfeed newsfeed = newsfeedRepository.findById(newsfeedId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.NEWSFEED_NOT_FOUND));

        User user = principalDetails.getUser();


        List<Comment> comments = commentRepository.findAll();

            List<CommentFindResponse> data = new ArrayList<>();

            for (Comment comment : comments) {
                if (comment.getParentCommentId() == null && comment.getNewsfeedId().getId().equals(newsfeedId)) {
                    data.add(new CommentFindResponse(comment));
                }
            }

            return GlobalResponse.success(status, message, data);
    }

    //댓글에 대한 대댓글 조회
    public GlobalResponse<List<CommentFindResponse>> getFromCommentsChildren(int status, String message, Long commentId) {

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.COMMENT_NOT_FOUND));

        List<Comment> children = commentRepository.findByParentCommentId_Id(comment.getId());
        List<CommentFindResponse> data = new ArrayList<>();
        for (Comment child : children) {
            data.add(new CommentFindResponse(child));
        }

        return GlobalResponse.success(status, message, data);
    }

    //댓글 수정
    public GlobalResponse<CommentPutResponse> update(int status, String message,Long commentId ,CommentPutRequest req, PrincipalDetails principalDetails) {

        User user = principalDetails.getUser();

        Comment comment = commentRepository
                .findById(commentId)
                .orElseThrow(()-> new NotFoundException(ErrorCode.COMMENT_NOT_FOUND));

        if (!comment.getUserId().getId().equals(user.getId())) {
            throw new NotFoundException(ErrorCode.USER_NOT_MATCH);
        }

        comment.update(req);
        commentRepository.save(comment);
        CommentPutResponse data = new CommentPutResponse(comment);
        return GlobalResponse.success(status, message, data);
    }

    //댓글 삭제
    public GlobalResponse<Void> delete(int status, String message, Long commentId, PrincipalDetails principalDetails) {

        User user = principalDetails.getUser();

        Comment comment = commentRepository
                .findById(commentId)
                .orElseThrow(()-> new NotFoundException(ErrorCode.COMMENT_NOT_FOUND));

        if (!comment.getUserId().getId().equals(user.getId())) {
            throw new NotFoundException(ErrorCode.USER_NOT_MATCH);
        }

        if (commentRepository.existsById(comment.getId())) {
            commentRepository.deleteById(comment.getId());
        }
        return GlobalResponse.successNodata(status, message);
    }
}