package com.sns.server.exceptions.account;

import com.sns.server.exceptions.BaseException;
import org.springframework.http.HttpStatus;

public class EmailConflictException extends BaseException {
    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.CONFLICT;
    }

    @Override
    public String getMessage() {
        return "이미 사용중이거나 탈퇴한 계정입니다.";
    }
}
