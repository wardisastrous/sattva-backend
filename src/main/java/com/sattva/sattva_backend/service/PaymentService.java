package com.sattva.sattva_backend.service;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.sattva.sattva_backend.model.Donation;
import com.sattva.sattva_backend.repository.DonationRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Service
public class PaymentService {

    @Value("${razorpay.key.id}")
    private String keyId;

    @Value("${razorpay.key.secret}")
    private String keySecret;

    @Autowired
    private DonationRepository donationRepository;

    // Create a Razorpay order
    public Map<String, Object> createOrder(double amount) throws RazorpayException {
        RazorpayClient client = new RazorpayClient(keyId, keySecret);

        JSONObject options = new JSONObject();
        options.put("amount", (int)(amount * 100)); // Convert to paise (₹1 = 100 paise)
        options.put("currency", "INR");
        options.put("receipt", "rcpt_" + System.currentTimeMillis());

        Order order = client.orders.create(options);

        // Save pending donation to database
        Donation donation = new Donation();
        donation.setAmount(amount);
        donation.setRazorpayOrderId(order.get("id"));
        donation.setStatus("pending");
        donationRepository.save(donation);

        // Return order details to frontend
        Map<String, Object> response = new HashMap<>();
        response.put("orderId", order.get("id"));
        response.put("amount", order.get("amount"));
        response.put("currency", order.get("currency"));
        return response;
    }

    // Verify payment signature (security check)
    public boolean verifyPayment(String orderId, String paymentId, String signature,
                                  String donorName, String donorEmail) {
        try {
            // Razorpay requires this exact verification method
            String data = orderId + "|" + paymentId;
            Mac sha256 = Mac.getInstance("HmacSHA256");
            sha256.init(new SecretKeySpec(keySecret.getBytes(StandardCharsets.UTF_8), "HmacSHA256"));
            byte[] hash = sha256.doFinal(data.getBytes(StandardCharsets.UTF_8));
            
            // Convert to hex string
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            boolean isValid = hexString.toString().equals(signature);

            if (isValid) {
                // Update donation status in database
                Donation donation = donationRepository.findByRazorpayOrderId(orderId);
                if (donation != null) {
                    donation.setRazorpayPaymentId(paymentId);
                    donation.setStatus("success");
                    donation.setDonorName(donorName);
                    donation.setDonorEmail(donorEmail);
                    donationRepository.save(donation);
                }
            }

            return isValid;
        } catch (Exception e) {
            return false;
        }
    }
}