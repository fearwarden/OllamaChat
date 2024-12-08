package com.fearwarden.OllamaChat.services;

import com.fearwarden.OllamaChat.dto.request.RegisterDto;
import com.fearwarden.OllamaChat.dto.response.UserDto;
import com.fearwarden.OllamaChat.exceptions.throwables.PasswordMissMatch;
import com.fearwarden.OllamaChat.exceptions.throwables.UserExistException;
import com.fearwarden.OllamaChat.exceptions.throwables.UserNotFoundException;
import com.fearwarden.OllamaChat.mappers.UserMapper;
import com.fearwarden.OllamaChat.models.User;
import com.fearwarden.OllamaChat.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UserMapper userMapper;

    public void register(RegisterDto body) {
        userRepository.findByEmail(body.getEmail()).ifPresent(user -> {
            throw new UserExistException();
        });

        if (!body.getPassword().equals(body.getConfirmationPassword())) {
            throw new PasswordMissMatch();
        }

        User user = new User();
        user.setEmail(body.getEmail());
        user.setFirstName(body.getFirstName());
        user.setLastName(body.getLastName());
        user.setPassword(passwordEncoder.encode(body.getPassword()));
        userRepository.save(user);
    }

    public UserDto login(String email, String password) {
        User user = userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new PasswordMissMatch();
        }
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        email,
                        password
                )
        );
        return userMapper.toDto(user);
    }
}
