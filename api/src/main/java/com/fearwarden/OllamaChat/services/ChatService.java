package com.fearwarden.OllamaChat.services;

import com.fearwarden.OllamaChat.dto.request.MessageDto;
import com.fearwarden.OllamaChat.enums.MessageType;
import com.fearwarden.OllamaChat.models.Chat;
import com.fearwarden.OllamaChat.models.User;
import com.fearwarden.OllamaChat.repositories.ChatRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ChatService {
    private final ChatRepository chatRepository;
    private final AIClientService aiClientService;
    private final UserService userService;
    private final MessageService messageService;

    @Transactional
    public String message(MessageDto body, UserDetails details) {
        User user = userService.findUserEntityByEmail(details.getUsername());
        Chat chat = createChat(body.getChatId(), user);
        chatRepository.save(chat);
        // Save user prompt
        messageService.saveMessage(body.getPrompt(), MessageType.PROMPT, chat.getId());
        String response = aiClientService.getResponse(body.getPrompt());
        // Save AI response
        messageService.saveMessage(response, MessageType.RESPONSE, chat.getId());
        return response;
    }

    private Chat createChat(String chatId, User user) {
        Chat chat;
        if (chatId == null) {
            chat = new Chat();
            chat.setUser(user);
        } else {
            chat = chatRepository.findById(UUID.fromString(chatId)).orElseThrow(null);
        }
        return chat;
    }
}
