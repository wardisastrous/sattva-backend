package com.sattva.sattva_backend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;
import java.time.LocalDateTime;

@Document(collection = "registrations")
@Data
public class EventRegistration {
    @Id
    private String id;
    private String eventId;
    private String email;
    private LocalDateTime registeredAt = LocalDateTime.now();
}