package com.fearwarden.OllamChat.exceptions.throwables;

public class PasswordMissMatch extends RuntimeException {
    public PasswordMissMatch() {
        super("Password and Confirmation password must be the same.");
    }
}
