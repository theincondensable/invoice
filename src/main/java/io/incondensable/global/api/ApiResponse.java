package io.incondensable.global.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

/**
 * @author abbas
 */
@Setter
@Getter
@AllArgsConstructor
public class ApiResponse<T> {
    private T data;
    private String message;
    private String description;
    private HttpStatus httpStatus;
}
