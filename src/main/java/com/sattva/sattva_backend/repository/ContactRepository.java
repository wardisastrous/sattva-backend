package com.sattva.sattva_backend.repository;

import com.sattva.sattva_backend.model.ContactMessage;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ContactRepository extends MongoRepository<ContactMessage, String> {}