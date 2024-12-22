package com.outpatient.controller;

import com.stripe.Stripe;
import com.stripe.model.PaymentIntent;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    public PaymentController() {
        Stripe.apiKey = "sk_test_51QXeFTFDMIdA4FuzkKFLXJ1D6HYFRQgISxXq7Ms2moKdRTnxEuc9l6ECI2TTD1BrFp67ibJncutHTOytWFZOoMOf003DEceUuQ"; // Replace with your Stripe secret key
    }

    @PostMapping("/create-payment-intent")
    public ResponseEntity<?> createPaymentIntent(@RequestBody Map<String, Object> paymentInfo) {
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("amount", paymentInfo.get("amount")); // Amount in cents
            params.put("currency", "usd");
            params.put("payment_method_types", new String[]{"card"});

            PaymentIntent paymentIntent = PaymentIntent.create(params);

            Map<String, String> response = new HashMap<>();
            response.put("clientSecret", paymentIntent.getClientSecret());
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping("/verify-payment")
    public ResponseEntity<?> verifyPayment(@RequestBody Map<String, String> request) {
        try {
            String paymentId = request.get("paymentId");
            PaymentIntent paymentIntent = PaymentIntent.retrieve(paymentId);
            boolean isPaymentSuccessful = "succeeded".equals(paymentIntent.getStatus());

            if (isPaymentSuccessful) {
                return ResponseEntity.ok("Payment verified successfully.");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Payment verification failed.");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error verifying payment: " + e.getMessage());
        }
    }

}
