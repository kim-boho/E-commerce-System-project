package com.groupad.backend.exception.UserExceptions.login;

public class IncorrectPasswordException extends LoginException {

    public IncorrectPasswordException(String msg) {
        super(msg);
    }

}