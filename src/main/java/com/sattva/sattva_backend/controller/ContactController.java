package com.sattva.sattva_backend.controller;

import com.sattva.sattva_backend.model.ContactMessage;
import com.sattva.sattva_backend.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contact")
public class ContactController {

    @Autowired
    private ContactRepository contactRepository;

    @PostMapping
    public ResponseEntity<String> sendMessage(@RequestBody ContactMessage message) {
        contactRepository.save(message);
        return ResponseEntity.ok("Message received! We'll get back to you soon.");
    }
}