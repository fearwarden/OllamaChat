package com.fearwarden.OllamaChat.services;

import com.fearwarden.OllamaChat.dto.request.MessageDto;
import com.fearwarden.OllamaChat.enums.MessageType;
import com.fearwarden.OllamaChat.exceptions.throwables.UserNotFoundException;
import com.fearwarden.OllamaChat.models.Chat;
import com.fearwarden.OllamaChat.models.Message;
import com.fearwarden.OllamaChat.models.User;
import com.fearwarden.OllamaChat.repositories.ChatRepository;
import com.fearwarden.OllamaChat.repositories.MessageRepository;
import com.fearwarden.OllamaChat.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ChatService {
    private final ChatRepository chatRepository;
    private final ChatClient chatClient;
    private final MessageRepository messageRepository;
    private final UserRepository userRepository;

    public ChatService(ChatRepository chatRepository, ChatClient.Builder chatClient, MessageRepository messageRepository, UserRepository userRepository) {
        this.chatRepository = chatRepository;
        this.chatClient = chatClient.build();
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public String message(MessageDto body, UserDetails details) {
        User user = userRepository.findByEmail(details.getUsername()).orElseThrow(UserNotFoundException::new);
        Chat chat = handleChat(body.getChatId(), user);
        chatRepository.save(chat);

        Message newMessage = createMessage(body.getPrompt(), MessageType.PROMPT, chat.getId());
        messageRepository.save(newMessage);

        String response = chatClient.prompt()
                .user(body.getPrompt())
                .call()
                .content();
        Message newResponse = createMessage(response, MessageType.RESPONSE, chat.getId());
        messageRepository.save(newResponse);
        return response;
    }

    private Chat handleChat(String chatId, User user) {
        Chat chat;
        if (chatId == null) {
            chat = new Chat();
            chat.setUser(user);
        } else {
            chat = chatRepository.findById(UUID.fromString(chatId)).orElseThrow(null);
        }
        return chat;
    }

    private Message createMessage(String message, MessageType type, UUID chatId) {
        Message newMessage = new Message();
        newMessage.setId(UUID.randomUUID());
        newMessage.setContent(message);
        newMessage.setChatId(chatId);
        newMessage.setType(type);
        return newMessage;
    }
}
