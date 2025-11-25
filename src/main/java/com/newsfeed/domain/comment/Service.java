package com.newsfeed.domain.comment;

import com.newsfeed.domain.comment.dto.CommentCreateReq;
import com.newsfeed.domain.comment.dto.CommentCreateRes;
import com.newsfeed.domain.comment.dto.CommentGetResponse;
import com.newsfeed.common.entity.Comment;

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

@org.springframework.stereotype.Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class Service {
    private final CommentRepository commentRepository;
    private final NewsfeedRepository newsfeedRepository;
    private final UserRepository userRepository;

    public GlobalResponse<CommentCreateRes> create(int status, String message, CommentCreateReq req, Long newsfeedId, Long parentCommentId) {

        Newsfeed newsfeed = newsfeedRepository.findById(newsfeedId).
                orElseThrow(() -> new NotFoundException(ErrorCode.NEWSFEED_NOT_FOUND));

        User user = userRepository.findById(req.getUserId())
                .orElseThrow(() -> new NotFoundException(ErrorCode.USER_NOT_FOUND));
        Comment parentComment = null;
        int depth = 0;
        if (!(parentCommentId == null)) {
            parentComment = commentRepository.findById(parentCommentId)
                    .orElseThrow(() -> new NotFoundException(ErrorCode.COMMENT_NOT_FOUND));
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

    public GlobalResponse<CommentGetResponse> find() {


        return null;
    }

}