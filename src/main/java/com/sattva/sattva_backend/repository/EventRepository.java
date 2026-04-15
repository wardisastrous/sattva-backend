package com.sattva.sattva_backend.repository;

import com.sattva.sattva_backend.model.Event;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

// MongoRepository<Event, String> means:
// - Event = the model class
// - String = the type of the ID field
// Spring automatically creates all basic CRUD methods!
public interface EventRepository extends MongoRepository<Event, String> {
    // Custom query: find events by category
    List<Event> findByCategory(String category);
    
    // find all events ordered by date (newest first)
    List<Event> findAllByOrderByDateAsc();
}