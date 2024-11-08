package com.fearwarden.OllamaChat.services;

import com.fearwarden.OllamaChat.dto.request.MessageDto;
import com.fearwarden.OllamaChat.dto.response.ChatDto;
import com.fearwarden.OllamaChat.enums.MessageType;
import com.fearwarden.OllamaChat.mappers.ChatMapper;
import com.fearwarden.OllamaChat.models.Chat;
import com.fearwarden.OllamaChat.models.User;
import com.fearwarden.OllamaChat.repositories.ChatRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ChatService {
    private final ChatRepository chatRepository;
    private final AIClientService aiClientService;
    private final UserService userService;
    private final MessageService messageService;
    private final ChatMapper chatMapper;

    @Transactional
    public String message(MessageDto body, UserDetails details) {
        User user = userService.findUserEntityByEmail(details.getUsername());
        Chat chat = getOrCreateChat(body.getChatId(), user);
        chatRepository.save(chat);
        String response = aiClientService.generateResponse(body.getPrompt());
        // Save user prompt
        messageService.saveMessage(body.getPrompt(), MessageType.PROMPT, chat.getId());
        // Save AI response
        messageService.saveMessage(response, MessageType.RESPONSE, chat.getId());
        return response;
    }

    public List<ChatDto> getAllChats(UserDetails details) {
        User user = userService.findUserEntityByEmail(details.getUsername());
        List<Chat> chats = chatRepository.findAllByUserOrderByCreatedAtDesc(user);
        return chats.stream().map(chatMapper::toDto).toList();
    }

    private Chat getOrCreateChat(String chatId, User user) {
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
