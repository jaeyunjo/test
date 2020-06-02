package com.sns.server.exceptions.account;

import com.sns.server.exceptions.BaseException;
import org.springframework.http.HttpStatus;

public class AccountNotAuthorizedException extends BaseException {

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.UNAUTHORIZED;
    }

    @Override
    public String getMessage() {
        return "유효하지 않은 인증 정보입니다.";
    }
}
