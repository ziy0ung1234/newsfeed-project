package com.newsfeed.common.exception;

import lombok.Getter;

@Getter
public class ConflictException extends RuntimeException {
    private final ErrorCode errorCode;
    public ConflictException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }
}
