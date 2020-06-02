package com.sns.server.common;

import com.sns.server.exceptions.BaseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(BaseException.class)
    public ResponseEntity<?> handleApiException(BaseException exception) {
        ErrorResponse response = ErrorResponse.builder()
                .status(exception.getHttpStatus())
                .message(exception.getMessage())
                .build();
        return ResponseEntity.status(exception.getHttpStatus()).body(response);
    }
}
