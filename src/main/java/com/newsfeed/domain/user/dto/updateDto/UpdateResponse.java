package com.newsfeed.domain.user.dto.updateDto;

import com.newsfeed.domain.user.entity.User;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UpdateResponse {

    private final Long id;
    private final String email;
    private final String username;
    private final String cellphone;
    private final LocalDateTime modifiedAt;

    private UpdateResponse(Long id, String email, String username, String cellphone, LocalDateTime modifiedAt) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.cellphone = cellphone;
        this.modifiedAt = modifiedAt;
    }

    public static UpdateResponse of(User user) {
        return new UpdateResponse(
                user.getId(),
                user.getEmail(),
                user.getUsername(),
                user.getCellPhoneNumber(),
                user.getModifiedAt());
    }
}
