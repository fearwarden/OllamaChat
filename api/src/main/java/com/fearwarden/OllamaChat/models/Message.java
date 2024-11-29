package com.fearwarden.OllamaChat.models;

import com.fearwarden.OllamaChat.enums.MessageType;
import com.fearwarden.OllamaChat.tools.Indices;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.elasticsearch.annotations.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@ToString
@Document(indexName = Indices.MESSAGE_INDEX)
@Mapping(mappingPath = "static/message.json")
public class Message {
    @Id
    @Field(type = FieldType.Keyword)
    private UUID id;
    @Field(type = FieldType.Text)
    private String content;
    @Field(type = FieldType.Date, format = {}, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private LocalDateTime createdAt = LocalDateTime.now();
    private UUID chatId;
    private MessageType type;
}
