package com.fearwarden.OllamaChat.repositories;

import com.fearwarden.OllamaChat.models.Chat;
import com.fearwarden.OllamaChat.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ChatRepository extends JpaRepository<Chat, UUID> {
    List<Chat> findAllByUserOrderByCreatedAtDesc(User user);
}