package com.sattva.sattva_backend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;
import java.time.LocalDateTime;

@Document(collection = "donations")
@Data
public class Donation {
    @Id
    private String id;
    private String donorName;
    private String donorEmail;
    private double amount;
    private String razorpayOrderId;
    private String razorpayPaymentId;
    private String status; // "pending", "success", "failed"
    private LocalDateTime createdAt = LocalDateTime.now();
}