package com.newsfeed.domain.user.dto.updatePasswordDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

/**
 * 비밀번호 변경 요청 DTO
 * - currentPassword : 현재 비밀번호
 * - newPassword : 새로 설정할 비밀번호
 * - 유효성 검사(정규식, 최소 길이, 필수 입력)를 모두 포함한다
 */
@Getter
public class UpdatePasswordRequest {

    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    @Size(min = 8, message = "비밀번호는 최소 8자 이상이어야 합니다.")
    @Pattern(
            regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[!@#$%^&*()_+=\\-{}\\[\\]:;\"'?/<>,.~`|]).{8,}$",
            message = "비밀번호는 대문자, 소문자, 숫자, 특수문자를 최소 1개씩 포함해야 합니다."
    )
    private final String currentPassword;

    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    @Size(min = 8, message = "비밀번호는 최소 8자 이상이어야 합니다.")
    @Pattern(
            regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[!@#$%^&*()_+=\\-{}\\[\\]:;\"'?/<>,.~`|]).{8,}$",
            message = "비밀번호는 대문자, 소문자, 숫자, 특수문자를 최소 1개씩 포함해야 합니다."
    )
    private final String newPassword;

    public UpdatePasswordRequest(String currentPassword, String newPassword) {
        this.currentPassword = currentPassword;
        this.newPassword = newPassword;
    }
}
