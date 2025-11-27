package com.newsfeed.domain.user.dto.userInfoDto;

import com.newsfeed.domain.user.entity.User;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UserInfoResponse {

    private final Long id;
    private final String email;
    private final String username;
    private final String cellphone;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    private UserInfoResponse(Long id, String email, String username, String cellphone, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.cellphone = cellphone;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    //로그인 된 본인 정보
    public static UserInfoResponse forMyInfo(User user) {
        return new UserInfoResponse(
                user.getId(),
                user.getEmail(),
                user.getUsername(),
                user.getCellPhoneNumber(),
                user.getCreatedAt(),
                user.getModifiedAt());
    }

    //다른 유저 정보
    public static UserInfoResponse forOtherInfo(User user) {
        return new UserInfoResponse(
                user.getId(),
                user.getEmail(),
                user.getUsername(),
                user.getCellPhoneNumber(),
                user.getCreatedAt(),
                user.getModifiedAt());
    }

}
