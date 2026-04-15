package com.sattva.sattva_backend.repository;

import com.sattva.sattva_backend.model.Donation;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DonationRepository extends MongoRepository<Donation, String> {
    Donation findByRazorpayOrderId(String orderId);
}