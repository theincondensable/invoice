package io.incondensable.auth.exceptions;

import io.incondensable.global.exception.model.BusinessException;
import io.incondensable.global.exception.model.ErrorDetails;
import org.springframework.http.HttpStatus;

/**
 * @author abbas
 */
public class PasswordMismatchException extends BusinessException {

    public PasswordMismatchException() {
        super(new ErrorDetails("entered.password.does.not.match", HttpStatus.UNAUTHORIZED, (Object) null));
    }

}
