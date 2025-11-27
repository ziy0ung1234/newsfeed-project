package com.newsfeed.domain.user.dto.updatePasswordDto;

import com.newsfeed.domain.user.entity.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 비밀번호 변경 결과를 내려주는 Response DTO
 * - 변경된 유저의 ID만 응답한다
 */
@Getter
@RequiredArgsConstructor
public class UpdatePasswordResponse {

    private Long id;

    public UpdatePasswordResponse(Long id) {
        this.id = id;
    }

    public static UpdatePasswordResponse of(User user) {
        return new UpdatePasswordResponse(user.getId());
    }
}
