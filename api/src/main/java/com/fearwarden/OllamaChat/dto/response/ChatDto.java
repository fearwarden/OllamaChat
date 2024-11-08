package com.fearwarden.OllamaChat.dto.response;

import lombok.Value;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * DTO for {@link com.fearwarden.OllamaChat.models.Chat}
 */
@Value
public class ChatDto implements Serializable {
    UUID id;
    String title;
    LocalDateTime createdAt;
}