package com.sns.server.exceptions;

import org.springframework.http.HttpStatus;

public abstract class BaseException extends RuntimeException {
    public abstract HttpStatus getHttpStatus();
    public abstract String getMessage();
}
