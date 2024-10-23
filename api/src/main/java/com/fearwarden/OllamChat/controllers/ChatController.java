package com.fearwarden.OllamChat.controllers;

import com.fearwarden.OllamChat.dto.request.MessageDto;
import com.fearwarden.OllamChat.services.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/chat")
@RequiredArgsConstructor
public class ChatController {
    private final ChatService chatService;

    @PostMapping("/message")
    public ResponseEntity<String> message(@RequestBody @Validated MessageDto body) {
        String llmResponse = chatService.message(body.getPrompt());
        return ResponseEntity.ok(llmResponse);
    }
}
