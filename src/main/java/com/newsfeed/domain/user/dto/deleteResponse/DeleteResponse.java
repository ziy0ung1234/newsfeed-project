package com.newsfeed.domain.user.dto.deleteResponse;

import lombok.Getter;

@Getter
public class DeleteResponse {

    private final Long id;
    private final String message;

    public DeleteResponse(Long id, String message) {
        this.id = id;
        this.message = message;
    }

    public static DeleteResponse of(Long id, String message) {
        return new DeleteResponse(id, message);
    }

}
