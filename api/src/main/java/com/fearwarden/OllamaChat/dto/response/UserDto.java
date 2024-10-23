package com.fearwarden.OllamaChat.dto.response;

import com.fearwarden.OllamaChat.enums.Role;
import com.fearwarden.OllamaChat.models.User;
import lombok.Value;

import java.io.Serializable;
import java.util.UUID;

/**
 * DTO for {@link User}
 */
@Value
public class UserDto implements Serializable {
    UUID id;
    String firstName;
    String lastName;
    String email;
    Role role;
}