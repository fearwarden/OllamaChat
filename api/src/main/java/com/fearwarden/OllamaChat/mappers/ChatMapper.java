package com.fearwarden.OllamaChat.mappers;

import com.fearwarden.OllamaChat.dto.response.ChatDto;
import com.fearwarden.OllamaChat.models.Chat;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ChatMapper {
    Chat toEntity(ChatDto chatDto);

    ChatDto toDto(Chat chat);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Chat partialUpdate(ChatDto chatDto, @MappingTarget Chat chat);
}