package com.newsfeed.domain.user.dto.updateDto;

import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;

@Getter
@NoArgsConstructor
public class UpdateRequest {

    @Email(message = "이메일 형식이 올바르지 않습니다.")
    private String email;
    private String username;
    private String cellphone;

}
