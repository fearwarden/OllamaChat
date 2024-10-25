package com.fearwarden.OllamaChat.repositories;

import com.fearwarden.OllamaChat.models.Message;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;
import java.util.UUID;

public interface MessageRepository extends ElasticsearchRepository<Message, UUID> {
    List<Message> findByChatIdOrderByCreatedAtDesc(UUID chatId);
    void deleteAllByChatId(UUID chatId);
}
