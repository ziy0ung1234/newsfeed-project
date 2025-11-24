package com.newsfeed.common.exception;

import lombok.Getter;

@Getter
public class LoginFailException extends RuntimeException {
    private final ErrorCode errorCode;
    public LoginFailException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }
}
