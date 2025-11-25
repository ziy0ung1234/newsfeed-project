package com.newsfeed.domain.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class SignUpRequest {
    private String username;
    private String email;
    private String password;
    private String cellPhoneNumber;
}
