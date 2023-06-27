package io.incondensable.global.exception.model;

/**
 * @author abbas
 */
public class BusinessException extends RuntimeException {
    private final ErrorDetails errorDetails;

    public BusinessException(ErrorDetails errorDetails) {
        super(errorDetails.getMessage());
        this.errorDetails = errorDetails;
    }

    public ErrorDetails getErrorDetails() {
        return errorDetails;
    }

}
