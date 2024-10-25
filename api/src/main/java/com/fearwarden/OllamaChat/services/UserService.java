package com.fearwarden.OllamaChat.services;

import com.fearwarden.OllamaChat.exceptions.throwables.UserNotFoundException;
import com.fearwarden.OllamaChat.models.User;
import com.fearwarden.OllamaChat.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User findUserEntityByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);
    }
}
