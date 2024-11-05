package com.fearwarden.OllamaChat.services;

import com.fearwarden.OllamaChat.dto.response.UserDto;
import com.fearwarden.OllamaChat.exceptions.throwables.UserNotFoundException;
import com.fearwarden.OllamaChat.mappers.UserMapper;
import com.fearwarden.OllamaChat.models.User;
import com.fearwarden.OllamaChat.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public User findUserEntityByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);
    }

    public UserDto me(UserDetails details) {
        User user = findUserEntityByEmail(details.getUsername());
        return userMapper.toDto(user);
    }
}
