package com.groupad.backend.exception.UserExceptions.signup;

public class InvalidPasswordException extends AccountCreationException {
    public InvalidPasswordException(String message) {
        super(message);
    }
}
