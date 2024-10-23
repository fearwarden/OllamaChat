package com.fearwarden.OllamaChat.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LoginDto {
    @Email(message = "Must be an email.")
    @NotBlank(message = "Email is required.")
    @NotNull(message = "Email is required.")
    private String email;
    @NotBlank(message = "Password is required.")
    @NotNull(message = "Password is required.")
    private String password;
}
