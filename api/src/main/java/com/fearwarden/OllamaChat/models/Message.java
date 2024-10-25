package com.fearwarden.OllamaChat.models;

import com.fearwarden.OllamaChat.enums.MessageType;
import com.fearwarden.OllamaChat.tools.Indices;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@ToString
@Document(indexName = Indices.MESSAGE_INDEX)
public class Message {
    @Id
    @Field(type = FieldType.Keyword)
    private UUID id;
    @Field(type = FieldType.Text)
    private String content;
    @Field(type = FieldType.Date)
    private LocalDateTime createdAt = LocalDateTime.now();
    private UUID chatId;
    private MessageType type;
}
