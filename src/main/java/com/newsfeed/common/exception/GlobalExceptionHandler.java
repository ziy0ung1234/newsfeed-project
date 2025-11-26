package com.newsfeed.common.exception;

import com.newsfeed.common.response.GlobalResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<GlobalResponse<Void>> notFoundException(NotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(GlobalResponse.exception(e.getErrorCode()));
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<GlobalResponse<Void>> unauthorizedException(UnauthorizedException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(GlobalResponse.exception(e.getErrorCode()));
    }

    @ExceptionHandler(LoginFailException.class)
    public ResponseEntity<GlobalResponse<Void>> loginFailException(LoginFailException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(GlobalResponse.exception(e.getErrorCode()));
    }

    @ExceptionHandler(SignUpFailException.class)
    public ResponseEntity<GlobalResponse<Void>> signUpFailException(SignUpFailException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(GlobalResponse.exception(e.getErrorCode()));
    }
}
