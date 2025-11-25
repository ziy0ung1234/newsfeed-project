package com.newsfeed.domain.user.dto.updatePasswordDto;

import com.newsfeed.domain.user.entity.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UpdatePasswordResponse {

    private Long id;
    private String message;

    public static UpdatePasswordResponse of(User user) {
        return new UpdatePasswordResponse(
                user.getId(),
                "비밀번호 변경 완료");
    }
}
