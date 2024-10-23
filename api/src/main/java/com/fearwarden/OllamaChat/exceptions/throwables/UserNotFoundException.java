package com.fearwarden.OllamaChat.exceptions.throwables;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
        super("User does not exist.");
    }
}
