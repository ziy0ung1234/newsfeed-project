package com.newsfeed.domain.newsfeed.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class NewsfeedRequest {

    @NotBlank(message = "제목을 입력해 주세요")
    @Size(max = 15, message = "제목은 15자 이내로 입력해주세요")
    private String title;
    @NotBlank(message = "내용을 입력해 주세요")
    @Size(max = 500, message =  "내용은 500자 이내로 입력해주세요")
    private String content;
}
