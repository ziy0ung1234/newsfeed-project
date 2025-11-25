package com.newsfeed.domain.user.dto.updatePasswordDto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class UpdatePasswordRequest {

    @NotBlank
    private final String currentPassword;

    @NotBlank
    private final String nwePassword;

    public UpdatePasswordRequest(String currentPassword, String nwePassword) {
        this.currentPassword = currentPassword;
        this.nwePassword = nwePassword;
    }
}
