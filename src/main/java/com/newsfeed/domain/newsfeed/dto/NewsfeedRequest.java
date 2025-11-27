package com.newsfeed.domain.newsfeed.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class NewsfeedRequest {

    @NotBlank(message = "제목을 입력해 주세요")
    @Size(max = 15)
    private String title;
    @Size(max = 500)
    @NotBlank(message = "내용을 입력해 주세요")
    private String content;
}
