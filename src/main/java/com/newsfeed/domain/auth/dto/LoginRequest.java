package com.newsfeed.domain.auth.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class LoginRequest {
    @NotBlank(message = "이메일은 필수 입력 값입니다.")
    @Size(max = 30, message = "이메일은 30자 이하로 입력해주세요.")
    private String email;

    @NotBlank(message = "비밀번호은 필수 입력 값입니다.")
    @Size(min = 8, message = "비밀번호는 최소 8자 이상이어야 합니다.")
    private String password;
}
