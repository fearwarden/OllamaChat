package com.fearwarden.OllamChat.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RegisterDto {
    @Email(message = "Invalid email")
    @NotNull(message = "email is required.")
    @NotBlank(message = "email is required.")
    private String email;
    @NotNull(message = "First Name is required.")
    @NotBlank(message = "First Name is required.")
    private String firstName;
    @NotNull(message = "Last Name is required.")
    @NotBlank(message = "Last Name is required.")
    private String lastName;
    @NotNull(message = "Password is required.")
    @NotBlank(message = "Password is required.")
    private String password;
    @NotNull(message = "Confirmation Password is required.")
    @NotBlank(message = "Confirmation Password is required.")
    private String confirmationPassword;
}
