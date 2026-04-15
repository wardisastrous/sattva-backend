package com.sattva.sattva_backend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;
import java.time.LocalDateTime;

@Document(collection = "contacts")
@Data
public class ContactMessage {
    @Id
    private String id;
    private String name;
    private String email;
    private String subject;
    private String message;
    private LocalDateTime sentAt = LocalDateTime.now();
}