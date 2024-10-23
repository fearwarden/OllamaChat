package com.fearwarden.OllamChat.services;

import com.fearwarden.OllamChat.repositories.ChatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class ChatService {
    private final ChatRepository chatRepository;
    private final ChatClient chatClient;

    public ChatService(ChatRepository chatRepository, ChatClient.Builder chatClient) {
        this.chatRepository = chatRepository;
        this.chatClient = chatClient.build();
    }

    public String message(String prompt) {
        return chatClient.prompt()
                .user(prompt)
                .call()
                .content();
    }
}
