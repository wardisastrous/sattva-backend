package com.sattva.sattva_backend.repository;

import com.sattva.sattva_backend.model.EventRegistration;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EventRegistrationRepository extends MongoRepository<EventRegistration, String> {
    boolean existsByEventIdAndEmail(String eventId, String email);
}