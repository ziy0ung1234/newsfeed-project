package common.exception;

import lombok.Getter;

@Getter
public class UserNotFoundException extends NotFoundException {
    public UserNotFoundException(ErrorCode error) {
        super(error.getMessage(), error.getStatus());
    }
}
