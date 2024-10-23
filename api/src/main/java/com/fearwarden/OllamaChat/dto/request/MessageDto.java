package com.fearwarden.OllamaChat.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MessageDto {
    @JsonIgnoreProperties(ignoreUnknown = true)
    private String chatId;
    @NotNull(message = "Prompt is required.")
    @NotBlank(message = "Prompt is required.")
    private String prompt;
}
