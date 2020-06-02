package com.sns.server.account;

import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.List;

@Component
public class AccountDtoValidator {
    public void validate(BindingResult bindingResult, Errors errors) {
        List<FieldError> errorFields = bindingResult.getFieldErrors();
        for (FieldError error : errorFields) {
            errors.rejectValue(error.getField(), String.valueOf(error.getRejectedValue()), error.getDefaultMessage());
        }
    }
}
