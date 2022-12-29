package com.groupad.backend.exception.UserExceptions.signup;

public class EmailAlreadyInUseException extends AccountCreationException {
    public EmailAlreadyInUseException(String email) {
        super(email + " already in use");
    }
}
