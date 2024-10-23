package com.fearwarden.OllamaChat.repositories;

import com.fearwarden.OllamaChat.models.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ChatRepository extends JpaRepository<Chat, UUID> {
}