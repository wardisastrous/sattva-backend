package com.sattva.sattva_backend.service;

import com.sattva.sattva_backend.model.Event;
import com.sattva.sattva_backend.model.EventRegistration;
import com.sattva.sattva_backend.repository.EventRepository;
import com.sattva.sattva_backend.repository.EventRegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

// @Service = Spring manages this class, and it contains business logic
@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private EventRegistrationRepository registrationRepository;

    @Value("${file.upload-dir}")
    private String uploadDir;

    // Get all events
    public List<Event> getAllEvents() {
        return eventRepository.findAllByOrderByDateAsc();
    }

    // Get single event
    public Event getEventById(String id) {
        return eventRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Event not found with id: " + id));
    }

    // Create new event with image
    public Event createEvent(String name, String date, String location, 
                              String description, String category,
                              MultipartFile image) throws IOException {
        Event event = new Event();
        event.setName(name);
        event.setDate(java.time.LocalDate.parse(date)); // Convert string to date
        event.setLocation(location);
        event.setDescription(description);
        event.setCategory(category);

        // Handle image upload
        if (image != null && !image.isEmpty()) {
            String imageUrl = saveImage(image);
            event.setImageUrl(imageUrl);
        }

        return eventRepository.save(event); // Save to MongoDB
    }

    // Save image to local folder
    private String saveImage(MultipartFile file) throws IOException {
        // Create uploads folder if it doesn't exist
        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        // Generate unique filename to avoid conflicts
        String filename = UUID.randomUUID() + "_" + file.getOriginalFilename();
        Path filePath = uploadPath.resolve(filename);
        Files.copy(file.getInputStream(), filePath); // Save the file

        return "/uploads/" + filename; // Return the URL path
    }

    // Delete event
    public void deleteEvent(String id) {
        eventRepository.deleteById(id);
    }

    // Register for event
    public void registerForEvent(String eventId, String email) {
        // Check if already registered
        if (registrationRepository.existsByEventIdAndEmail(eventId, email)) {
            throw new RuntimeException("You are already registered for this event!");
        }

        EventRegistration registration = new EventRegistration();
        registration.setEventId(eventId);
        registration.setEmail(email);
        registrationRepository.save(registration);

        // Increment registration count on event
        Event event = getEventById(eventId);
        event.setRegistrations(event.getRegistrations() + 1);
        eventRepository.save(event);
    }
}