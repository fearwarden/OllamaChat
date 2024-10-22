package com.fearwarden.OllamChat.exceptions.throwables;

public class UserExistException extends RuntimeException {
    public UserExistException() {
        super("Email already exist.");
    }
}
