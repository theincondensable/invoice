package io.incondensable.global.exception.model;

import org.springframework.http.HttpStatus;

/**
 * @author abbas
 */
public final class ErrorDetails {
    private final String message;
    private final HttpStatus httpStatus;
    private final Object[] args;

    public ErrorDetails(String message, HttpStatus httpStatus, Object... args) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.args = args;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public Object[] getArgs() {
        return args;
    }

}
