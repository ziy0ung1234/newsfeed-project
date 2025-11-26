package com.newsfeed.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.newsfeed.common.exception.ErrorCode;
import com.newsfeed.domain.user.dto.userInfoDto.UserInfoResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GlobalResponse<T> {
    private int status;
    private String message;
    private T data;

    //성공시
    public static <T> GlobalResponse<T> success(int status, String message, T data) {
        return new GlobalResponse<>(status, message, data);
    }
    //예외처리시
    public static GlobalResponse<Void> exception(ErrorCode errorCode) {
        return new GlobalResponse<>(errorCode.getStatus(), errorCode.getMessage(), null);
    }
    //성공했는데 응답데이터는 없을시
    public static GlobalResponse<Void> successNodata(int status, String message) {
        return new GlobalResponse<>(status, message, null);
    }
}
