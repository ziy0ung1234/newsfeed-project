package com.newsfeed.domain.user.dto.updateDto;

import jakarta.validation.constraints.*;
import lombok.Getter;

@Getter
public class UpdateRequest {

    @Email(message = "이메일 형식이 올바르지 않습니다.")
    private String email;

    @Size(min = 1, max = 20, message = "이름은 1~20자 사이여야 합니다.")
    private String username;

    @Size(min = 11, max = 11, message = "휴대폰 번호는 010 포함 11자리여야 합니다.")
    @PositiveOrZero
    private String cellphone;

    public UpdateRequest(String email, String username, String cellphone) {
        this.email = email;
        this.username = username;
        this.cellphone = cellphone;
    }
}
