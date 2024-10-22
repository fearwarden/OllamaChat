package com.fearwarden.OllamChat.services;

import com.fearwarden.OllamChat.dto.request.RegisterDto;
import com.fearwarden.OllamChat.dto.response.UserDto;
import com.fearwarden.OllamChat.exceptions.throwables.PasswordMissMatch;
import com.fearwarden.OllamChat.exceptions.throwables.UserExistException;
import com.fearwarden.OllamChat.exceptions.throwables.UserNotFoundException;
import com.fearwarden.OllamChat.mappers.UserMapper;
import com.fearwarden.OllamChat.models.User;
import com.fearwarden.OllamChat.repositories.UserRepository;
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

        // TODO: handle this validation with annotation in dto
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
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        email,
                        password
                )
        );
        User user = userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);
        return userMapper.toDto(user);
    }
}
