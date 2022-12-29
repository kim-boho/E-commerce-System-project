package com.groupad.backend.exception.UserExceptions.signup;

public class AccountCreationException extends Exception {
    public AccountCreationException(String email) {
        super("Error Creating Account: " + email);
    }
}
