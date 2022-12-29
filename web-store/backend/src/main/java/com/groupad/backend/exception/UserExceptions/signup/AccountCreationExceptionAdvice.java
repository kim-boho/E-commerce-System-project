package com.groupad.backend.exception.UserExceptions.signup;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
class AccountCreationExceptionAdvice {

    @ResponseBody
    @ExceptionHandler(AccountCreationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String accountCreationExceptionHandler(AccountCreationException ex) {
        return ex.getMessage();
    }
}