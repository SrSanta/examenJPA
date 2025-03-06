package com.example.examenjpa.exception;

public class UserNotFountException extends RuntimeException {
    public UserNotFountException(Long id) {
        super("Not found User with id: " + id);
    }
}
