package com.groupad.backend.exception.UserExceptions.signup;

public class InvalidEmailException extends AccountCreationException {
    public InvalidEmailException(String email) {
        super(email + " invalid");
    }
}
