package com.fearwarden.OllamaChat.services;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.InMemoryChatMemory;
import org.springframework.stereotype.Service;

@Service
public class AIClientService {
    private final ChatClient chatClient;

    public AIClientService(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    public String getResponse(String prompt) {
        return chatClient.prompt()
                .user(prompt)
                .call()
                .content();
    }
}
