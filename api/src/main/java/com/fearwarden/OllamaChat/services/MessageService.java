package com.fearwarden.OllamaChat.services;

import com.fearwarden.OllamaChat.enums.MessageType;
import com.fearwarden.OllamaChat.models.Message;
import com.fearwarden.OllamaChat.repositories.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final MessageRepository messageRepository;

    public List<Message> getAllMessages(UUID chatId) {
        return messageRepository.findByChatIdOrderByCreatedAtDesc(chatId);
    }

    private Message createMessage(String content, MessageType type, UUID chatId) {
        Message message = new Message();
        message.setId(UUID.randomUUID());
        message.setContent(content);
        message.setChatId(chatId);
        message.setType(type);
        return message;
    }

    public void saveMessage(String content, MessageType type, UUID chatId) {
        Message message = createMessage(content, type, chatId);
        messageRepository.save(message);
    }

    public void deleteAllMessages() {
        messageRepository.deleteAll();
    }
}
