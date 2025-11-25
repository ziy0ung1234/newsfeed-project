package com.newsfeed.domain.user.dto.updateDto;

import jakarta.validation.constraints.Email;
import lombok.Getter;

@Getter
public class UpdateRequest {

    @Email(message = "이메일 형식이 올바르지 않습니다.")
    private String email;
    private String username;
    private String cellphone;

    public UpdateRequest(String email, String username, String cellphone) {
        this.email = email;
        this.username = username;
        this.cellphone = cellphone;
    }
}
