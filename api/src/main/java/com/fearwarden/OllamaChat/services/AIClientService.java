package com.fearwarden.OllamaChat.services;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.InMemoryChatMemory;
import org.springframework.stereotype.Service;

@Service
public class AIClientService {
    private final ChatClient chatClient;
    private static final String SYSTEM_PROMPT = """
            You are a helpful AI assistant that is specialized in Java and Spring, Spring Boot.
            Use the provided conversation history to maintain context.
            Previous conversation: {history}
            """;

    // TODO: add vector store - elasticsearch vector store
    public AIClientService(ChatClient.Builder builder) {
        this.chatClient = builder
                .defaultAdvisors(new MessageChatMemoryAdvisor(new InMemoryChatMemory()))
                .build();
    }

    public String generateResponse(String prompt) {
        return chatClient.prompt()
                .system(SYSTEM_PROMPT)
                .user(prompt)
                .call()
                .content();
    }

    public String generateChatTitle(String prompt) {
        String titleSystemPrompt = "For a given prompt, generate the title of the chat.";
        return chatClient.prompt()
                .system(titleSystemPrompt)
                .user(prompt).
                call()
                .content();
    }
}
