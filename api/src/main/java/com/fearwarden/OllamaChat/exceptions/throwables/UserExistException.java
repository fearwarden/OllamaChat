package com.fearwarden.OllamaChat.exceptions.throwables;

public class UserExistException extends RuntimeException {
    public UserExistException() {
        super("Email already exist.");
    }
}
