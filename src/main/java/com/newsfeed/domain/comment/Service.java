package com.newsfeed.domain.comment;

import com.newsfeed.domain.comment.dto.*;
import com.newsfeed.domain.comment.entity.Comment;

import com.newsfeed.common.exception.ErrorCode;
import com.newsfeed.common.exception.NotFoundException;
import com.newsfeed.common.response.GlobalResponse;
import com.newsfeed.domain.comment.repository.CommentRepository;
import com.newsfeed.domain.newsfeed.entity.Newsfeed;
import com.newsfeed.domain.newsfeed.repository.NewsfeedRepository;
import com.newsfeed.domain.user.entity.User;
import com.newsfeed.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
@Transactional
public class Service {
    private final CommentRepository commentRepository;
    private final NewsfeedRepository newsfeedRepository;
    private final UserRepository userRepository;

    public GlobalResponse<CommentCreateRes> create(int status, String message, CommentCreateReq req, Long newsfeedId, Long parentCommentId) {

        Newsfeed newsfeed = newsfeedRepository.findById(newsfeedId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.NEWSFEED_NOT_FOUND));

        User user = userRepository.findById(req.getUserId())
                .orElseThrow(() -> new NotFoundException(ErrorCode.USER_NOT_FOUND));

        //생성시에 부모가 없는 새 댓글일 수 있음. null, depth = 0 으로 새로 계층이 형성되는 댓글시에는 해당 변수 사용
        Comment parentComment = null;
        int depth = 0;

        //부모댓글이 있는 경우. 상위 뎁스를 확인한 뒤 그 depth보다 +1 만큼만 더해줌.
        if (!(parentCommentId == null)) {
            parentComment = commentRepository.findById(parentCommentId)
                    .orElseThrow(() -> new NotFoundException(ErrorCode.COMMENT_NOT_FOUND));
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

    public GlobalResponse<List<CommentWithChildrenResponse>> find(int status, String message) {
            List<Comment> comments = commentRepository.findAll();

            List<CommentWithChildrenResponse> data = new ArrayList<>();

            for (Comment comment : comments) {
                if (comment.getParentCommentId() == null) {
                    data.add(new CommentWithChildrenResponse(comment));
                } else {
                    data.stream()
                            .filter(c -> c.getId().equals(comment.getParentCommentId().getId()))
                            .findFirst()
                            .ifPresent(parent -> parent.addChild(new CommentWithChildrenResponse(comment)));
                }
            }

            return GlobalResponse.success(status, message, data);
    }

    public GlobalResponse<CommentWithChildrenResponse> findDetail(int status, String message, Long commentId) {

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.COMMENT_NOT_FOUND));

        List<Comment> children = commentRepository.findByParentCommentId_Id(comment.getId());

        CommentWithChildrenResponse data = new CommentWithChildrenResponse(comment);

        for (Comment child : children) {
            CommentWithChildrenResponse c = new CommentWithChildrenResponse(child);
            data.addChild(c);
        }

        return GlobalResponse.success(status, message, data);
    }

    public GlobalResponse<CommentPutResponse> update(int status, String message,Long commentId ,CommentPutRequest req) {

        Comment comment = commentRepository
                .findById(commentId)
                .orElseThrow(()-> new NotFoundException(ErrorCode.COMMENT_NOT_FOUND));

        comment.update(req);
        commentRepository.save(comment);
        CommentPutResponse data = new CommentPutResponse(comment);
        return GlobalResponse.success(status, message, data);
    }

    public GlobalResponse<Void> delete(int status, String message, Long commentId) {
        Comment comment = commentRepository
                .findById(commentId)
                .orElseThrow(()-> new NotFoundException(ErrorCode.COMMENT_NOT_FOUND));

        if (commentRepository.existsById(comment.getId())) {
            commentRepository.deleteById(comment.getId());
        }
        return GlobalResponse.successNodata(status, message);
    }
}