package com.sattva.sattva_backend.controller;

import com.sattva.sattva_backend.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    // POST /api/payments/create-order - Create Razorpay order
    @PostMapping("/create-order")
    public ResponseEntity<?> createOrder(@RequestBody Map<String, Object> body) {
        try {
            double amount = Double.parseDouble(body.get("amount").toString());
            Map<String, Object> order = paymentService.createOrder(amount);
            return ResponseEntity.ok(order);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    // POST /api/payments/verify - Verify Razorpay payment
    @PostMapping("/verify")
    public ResponseEntity<?> verifyPayment(@RequestBody Map<String, String> body) {
        boolean isValid = paymentService.verifyPayment(
            body.get("razorpay_order_id"),
            body.get("razorpay_payment_id"),
            body.get("razorpay_signature"),
            body.get("donorName"),
            body.get("donorEmail")
        );

        if (isValid) {
            return ResponseEntity.ok(Map.of("status", "Payment verified successfully"));
        } else {
            return ResponseEntity.badRequest().body(Map.of("error", "Payment verification failed"));
        }
    }
}