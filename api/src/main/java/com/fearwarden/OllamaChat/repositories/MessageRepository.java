package com.fearwarden.OllamaChat.repositories;

import com.fearwarden.OllamaChat.models.Message;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.UUID;

public interface MessageRepository extends ElasticsearchRepository<Message, UUID> {
}
