package com.fearwarden.OllamaChat.controllers;

import com.fearwarden.OllamaChat.dto.request.MessageDto;
import com.fearwarden.OllamaChat.dto.response.ChatDto;
import com.fearwarden.OllamaChat.services.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/chat")
@RequiredArgsConstructor
public class ChatController {
    private final ChatService chatService;

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
}
