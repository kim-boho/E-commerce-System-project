package com.groupad.backend.exception.UserExceptions.login;

public class UserNotFoundException extends LoginException {

    public UserNotFoundException(String email) {
        super("Couldn't find user with email: " + email);
    }

}