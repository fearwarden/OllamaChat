package com.fearwarden.OllamaChat.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MessageDto {
    @NotNull(message = "Prompt is required.")
    @NotBlank(message = "Prompt is required.")
    private String prompt;
}
