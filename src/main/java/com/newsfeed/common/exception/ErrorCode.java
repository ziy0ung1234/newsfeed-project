package com.newsfeed.common.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
    //------404-----------------------
    USER_NOT_FOUND(404, "없는 유저입니다"),
    NEWSFEED_NOT_FOUND(404, "없는 피드입니다"),
    COMMENT_NOT_FOUND(404, "없는 댓글입니다"),
    LIKE_NOT_FOUND(404, "취소할 좋아요가 없습니다."),
    //------403-----------------------
    USER_NOT_MATCH(403, "접근 권한이 없습니다"),
    FORBIDDEN(403, "접근 권한이 없습니다"),
    //------401-----------------------
    LOGIN_REQUIRED(401, "로그인한 유저만 사용할 수 있는 기능입니다");

    private final int status;
    private final String message;

    ErrorCode(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
