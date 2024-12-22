package com.outpatient.service;

import com.stripe.Stripe;
import com.stripe.model.PaymentIntent;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class PaymentService {

    public PaymentService() {
        // Initialize Stripe API with your secret key
        Stripe.apiKey = "sk_test_51QXeFTFDMIdA4FuzkKFLXJ1D6HYFRQgISxXq7Ms2moKdRTnxEuc9l6ECI2TTD1BrFp67ibJncutHTOytWFZOoMOf003DEceUuQ";
    }

    /**
     * Creates a PaymentIntent on Stripe.
     * @param amount Amount in cents (e.g., $10 = 1000)
     * @param currency Currency (e.g., "usd")
     * @return PaymentIntent client secret
     * @throws Exception if creation fails
     */
    public String createPaymentIntent(int amount, String currency) throws Exception {
        try {
            var params = new HashMap<String, Object>();
            params.put("amount", amount);
            params.put("currency", currency);
            params.put("payment_method_types", List.of("card"));

            PaymentIntent paymentIntent = PaymentIntent.create(params);
            return paymentIntent.getClientSecret(); // Return client secret for frontend
        } catch (Exception e) {
            throw new Exception("Error creating PaymentIntent: " + e.getMessage(), e);
        }
    }

    /**
     * Verifies a PaymentIntent by its ID.
     * @param paymentId PaymentIntent ID
     * @return true if payment is verified, false otherwise
     */
    public boolean verifyPayment(String paymentId) {
        try {
            PaymentIntent paymentIntent = PaymentIntent.retrieve(paymentId);
            return "succeeded".equals(paymentIntent.getStatus());
        } catch (Exception e) {
            return false; // Verification failed
        }
    }
}
