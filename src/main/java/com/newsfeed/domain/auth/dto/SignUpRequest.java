package com.newsfeed.domain.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class SignUpRequest {

    @NotBlank(message = "이름은 필수 입력 값입니다.")
    @Size(max = 30, message = "이름은 30자 이하로 입력해주세요.")
    private String username;

    @Email
    @NotBlank(message = "이메일은 필수 입력 값입니다.")
    @Size(max = 30, message = "이메일은 30자 이하로 입력해주세요.")
    private String email;

    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    @Size(min = 8, max = 50, message = "비밀번호는 8자 이상, 50자 이하여야 합니다.")
    @Pattern(
            regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[^A-Za-z0-9]).+$",
            message = "비밀번호는 대문자, 소문자, 숫자, 특수문자를 최소 1개씩 포함해야 합니다."
    )
    private String password;

    @NotBlank(message = "전화번호는 필수 입력 값입니다.")
    @Pattern(regexp = "^010-\\d{3,4}-\\d{4}$",
            message = "휴대폰 번호는 010으로 시작하는 11자리 숫자와 '-'로 구성되어야 합니다.")
    private String cellPhoneNumber;
}
