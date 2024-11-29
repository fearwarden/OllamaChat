package com.fearwarden.OllamaChat.controllers;

import com.fearwarden.OllamaChat.dto.request.MessageDto;
import com.fearwarden.OllamaChat.dto.response.ChatDto;
import com.fearwarden.OllamaChat.models.Message;
import com.fearwarden.OllamaChat.services.ChatService;
import com.fearwarden.OllamaChat.services.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/chat")
@RequiredArgsConstructor
public class ChatController {
    private final ChatService chatService;
    private final MessageService messageService;

    @PostMapping("/message")
    public ResponseEntity<String> message(@RequestBody @Validated MessageDto body, @AuthenticationPrincipal UserDetails details) {
        String llmResponse = chatService.message(body, details);
        return ResponseEntity.ok(llmResponse);
    }

    @GetMapping
    public ResponseEntity<List<ChatDto>> getAllChats(@AuthenticationPrincipal UserDetails details) {
        List<ChatDto> chats = chatService.getAllChats(details);
        return ResponseEntity.ok(chats);
    }

    @GetMapping("/{chatId}/messages")
    public ResponseEntity<List<Message>> getAllMessages(@PathVariable String chatId) {
        return ResponseEntity.ok(messageService.getAllMessages(UUID.fromString(chatId)));
    }

    @DeleteMapping("/messages")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteAll() {
        messageService.deleteAllMessages();
    }
}
