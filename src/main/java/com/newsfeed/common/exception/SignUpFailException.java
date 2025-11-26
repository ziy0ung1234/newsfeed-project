package com.newsfeed.common.exception;

import lombok.Getter;

@Getter
public class SignUpFailException extends RuntimeException {
    private final ErrorCode errorCode;

    public SignUpFailException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }
}
