package com.sattva.sattva_backend.service;

import com.sattva.sattva_backend.model.Event;
import com.sattva.sattva_backend.model.EventRegistration;
import com.sattva.sattva_backend.repository.EventRepository;
import com.sattva.sattva_backend.repository.EventRegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private EventRegistrationRepository registrationRepository;

    @Autowired
    private CloudinaryService cloudinaryService; // ← Use Cloudinary now

    public List<Event> getAllEvents() {
        return eventRepository.findAllByOrderByDateAsc();
    }

    public Event getEventById(String id) {
        return eventRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Event not found: " + id));
    }

    public Event createEvent(String name, String date, String location,
                              String description, String category,
                              MultipartFile image) throws IOException {
        Event event = new Event();
        event.setName(name);
        event.setDate(LocalDate.parse(date));
        event.setLocation(location);
        event.setDescription(description);
        event.setCategory(category != null ? category : "General");

        // Upload to Cloudinary if image provided
        if (image != null && !image.isEmpty()) {
            String imageUrl = cloudinaryService.uploadImage(image);
            event.setImageUrl(imageUrl); // This is now a permanent Cloudinary URL
        }

        return eventRepository.save(event);
    }

    public void deleteEvent(String id) {
        eventRepository.deleteById(id);
    }

    public void registerForEvent(String eventId, String email) {
        if (registrationRepository.existsByEventIdAndEmail(eventId, email)) {
            throw new RuntimeException("Already registered for this event!");
        }
        EventRegistration reg = new EventRegistration();
        reg.setEventId(eventId);
        reg.setEmail(email);
        registrationRepository.save(reg);

        Event event = getEventById(eventId);
        event.setRegistrations(event.getRegistrations() + 1);
        eventRepository.save(event);
    }
}
