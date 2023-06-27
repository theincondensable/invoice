package io.incondensable.auth.exceptions;

import io.incondensable.global.exception.model.BusinessException;
import io.incondensable.global.exception.model.ErrorDetails;
import org.springframework.http.HttpStatus;

/**
 * @author abbas
 */
public class UserNotFoundException extends BusinessException {

    public UserNotFoundException(String username) {
        super(new ErrorDetails("user.not.found.with.give.username", HttpStatus.NOT_FOUND, username));
    }

}
