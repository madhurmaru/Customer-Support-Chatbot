package com.chatbot;

import java.util.HashMap;
import java.util.Map;

public class Chatbot {
    private static final Map<String, String> responses = new HashMap<>();

    static {
        responses.put("order status", "Please provide your order ID to check the status. You can also ask about 'Return Policy' or 'Shipping Info'.");
        responses.put("return policy", "Our return policy allows returns within 30 days of purchase. Would you like to know about 'Refund Policy'?");
        responses.put("refund policy", "Refunds are processed within 5-7 business days after we receive the returned item. Any other questions?");
        responses.put("shipping info", "Standard shipping usually takes 3-5 business days. Need help with 'Order Status'?");
        responses.put("payment options", "We accept all major credit cards, PayPal, and Apple Pay. Need help with something else?");
        responses.put("bye", "Thank you for chatting with us! Have a great day!");
    }

    public static String respond(String userInput) {
        String lowercaseInput = userInput.toLowerCase();

        for (String keyword : responses.keySet()) {
            if (lowercaseInput.contains(keyword)) {
                return responses.get(keyword);
            }
        }

        return "I'm here to help! Could you please provide more details about your query?";
    }
}
