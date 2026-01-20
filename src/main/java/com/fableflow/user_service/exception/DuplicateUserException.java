package com.fableflow.user_service.exception;

public class DuplicateUserException extends RuntimeException {

    public DuplicateUserException(String email) {
        super("User already exists with email: " + email);
    }
}
