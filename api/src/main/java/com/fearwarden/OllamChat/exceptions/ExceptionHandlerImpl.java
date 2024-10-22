package com.fearwarden.OllamChat.exceptions;

import com.fearwarden.OllamChat.exceptions.throwables.PasswordMissMatch;
import com.fearwarden.OllamChat.exceptions.throwables.UserExistException;
import com.fearwarden.OllamChat.exceptions.throwables.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerImpl {

    @ResponseBody
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> userNotFoundHandler(UserNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ResponseBody
    @ExceptionHandler(UserExistException.class)
    public ResponseEntity<String> userExistHandler(UserExistException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }

    @ResponseBody
    @ExceptionHandler(PasswordMissMatch.class)
    public ResponseEntity<String> passwordMissMatchHandler(PasswordMissMatch ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.FORBIDDEN);
    }
}
