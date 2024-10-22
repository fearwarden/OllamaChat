package com.fearwarden.OllamChat.dto.response;

import com.fearwarden.OllamChat.enums.Role;
import lombok.Value;

import java.io.Serializable;
import java.util.UUID;

/**
 * DTO for {@link com.fearwarden.OllamChat.models.User}
 */
@Value
public class UserDto implements Serializable {
    UUID id;
    String firstName;
    String lastName;
    String email;
    Role role;
}