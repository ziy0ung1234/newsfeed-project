package com.newsfeed.common.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
    //------410-----------------------
    USER_ALREADY_DELETED(410, "이미 탈퇴된 사용자입니다."),
    //------409-----------------------
    USER_ALREADY_EXISTS(409, "이미 존재하는 사용자 이메일입니다."),
    CELLPHONENUMBER_ALREADY_EXISTS(409, "이미 존재하는 전화번호입니다."),
    //------404-----------------------
    USER_NOT_FOUND(404, "없는 유저입니다"),
    NEWSFEED_NOT_FOUND(404, "없는 피드입니다"),
    COMMENT_NOT_FOUND(404, "없는 댓글입니다"),
    //------403-----------------------
    USER_NOT_MATCH(403, "접근 권한이 없습니다"),
    PASSWORD_NOT_MATCH(403, "비밀번호가 일치하지 않습니다."),
    //------401-----------------------
    LOGIN_REQUIRED(401, "로그인한 유저만 사용할 수 있는 기능입니다"),
    //------400-----------------------
    INVALID_EMAIL_FORMAT(400, "이메일 형식이 올바르지 않습니다."),
    INVALID_PASSWORD_FORMAT(400, "비밀번호 형식이 올바르지 않습니다."),
    INVALID_PASSWORD(400, "기존 비밀번호와 동일합니다.");


    private final int status;
    private final String message;

    ErrorCode(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
