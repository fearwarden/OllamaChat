package com.fearwarden.OllamChat.repositories;

import com.fearwarden.OllamChat.models.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ChatRepository extends JpaRepository<Chat, UUID> {
}