package com.sattva.sattva_backend.controller;

import com.sattva.sattva_backend.model.Event;
import com.sattva.sattva_backend.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
import java.util.Map;

// @RestController = This class handles HTTP requests and returns JSON
// @RequestMapping = All endpoints in this class start with /api/events
@RestController
@RequestMapping("/api/events")
public class EventController {

    @Autowired
    private EventService eventService;

    // GET /api/events - Returns all events
    @GetMapping
    public ResponseEntity<List<Event>> getAllEvents() {
        return ResponseEntity.ok(eventService.getAllEvents());
    }

    // GET /api/events/{id} - Returns one event
    @GetMapping("/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable String id) {
        return ResponseEntity.ok(eventService.getEventById(id));
    }

    // POST /api/events - Create new event (admin only - protected by SecurityConfig)
    @PostMapping
    public ResponseEntity<Event> createEvent(
            @RequestParam String name,
            @RequestParam String date,
            @RequestParam String location,
            @RequestParam String description,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) MultipartFile image) {
        try {
            Event event = eventService.createEvent(name, date, location, description, category, image);
            return ResponseEntity.ok(event);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // DELETE /api/events/{id} - Delete event (admin only)
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEvent(@PathVariable String id) {
        eventService.deleteEvent(id);
        return ResponseEntity.ok("Event deleted successfully");
    }

    // POST /api/events/{id}/register - Register for event
    @PostMapping("/{id}/register")
    public ResponseEntity<String> registerForEvent(
            @PathVariable String id,
            @RequestBody Map<String, String> body) {
        try {
            eventService.registerForEvent(id, body.get("email"));
            return ResponseEntity.ok("Successfully registered!");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}