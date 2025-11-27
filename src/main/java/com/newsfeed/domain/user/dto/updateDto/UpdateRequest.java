package com.newsfeed.domain.user.dto.updateDto;

import jakarta.validation.constraints.*;
import lombok.Getter;

/**
 * 사용자 프로필 수정 요청 DTO
 * - 수정 가능한 필드를 받는다 (email, username, cellphone)
 * - null 허용 → 필요한 값만 수정 가능
 * - 값이 존재할 경우에만 유효성 검증 적용
 */
@Getter
public class UpdateRequest {

    @Email
    @NotBlank(message = "이메일은 필수 입력 값입니다.")
    @Size(max = 30, message = "이메일은 30자 이하로 입력해주세요.")
    private String email;

    @NotBlank(message = "이름은 필수 입력 값입니다.")
    @Size(max = 30, message = "이름은 30자 이하로 입력해주세요.")
    private String username;

    @NotBlank(message = "전화번호는 필수 입력 값입니다.")
    @Pattern(regexp = "^010-\\d{3,4}-\\d{4}$",
            message = "휴대폰 번호는 010으로 시작하는 11자리 숫자와 '-'로 구성되어야 합니다.")
    private String cellphone;

    public UpdateRequest(String email, String username, String cellphone) {
        this.email = email;
        this.username = username;
        this.cellphone = cellphone;
    }
}
