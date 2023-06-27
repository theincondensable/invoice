package io.incondensable.global.exception;

import io.incondensable.global.exception.model.BusinessException;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;
import java.util.Locale;

/**
 * @author abbas
 */
@RestControllerAdvice
public class ExceptionalHandler {

    private final MessageSource messageSource;

    public ExceptionalHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<String> businessExceptionHandler(BusinessException exception) {
        return ResponseEntity
                .status(exception.getErrorDetails().getHttpStatus())
                .body(getErrorMessage(exception));
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<String> accessDeniedException() {
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body("نقش کاربر مناسب نیست.");
    }

    private String getErrorMessage(BusinessException exception) {
        return messageSource.getMessage(
                exception.getErrorDetails().getMessage(),
                exception.getErrorDetails().getArgs(),
                Locale.getDefault()
        );
    }

}
