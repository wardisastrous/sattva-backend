package com.sattva.sattva_backend.model;

// @Document tells MongoDB this is a collection (like a table)
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data; // Generates getters, setters, constructors automatically
import java.time.LocalDate;

@Document(collection = "events") // MongoDB collection name
@Data // Lombok: auto-generates getters, setters, toString
public class Event {
    
    @Id // This field is the primary key in MongoDB
    private String id;
    
    private String name;
    private LocalDate date;
    private String location;
    private String description;
    private String category;
    private String imageUrl; // URL or file path to event image
    private int registrations = 0; // Count of registrations
}