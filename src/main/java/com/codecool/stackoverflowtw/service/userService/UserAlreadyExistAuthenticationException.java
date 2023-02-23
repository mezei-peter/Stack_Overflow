package com.codecool.stackoverflowtw.service.userService;

import javax.naming.AuthenticationException;

public class UserAlreadyExistAuthenticationException extends AuthenticationException {

    public UserAlreadyExistAuthenticationException(String explanation) {
        super(explanation);
    }
}
