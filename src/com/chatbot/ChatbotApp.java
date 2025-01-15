package com.chatbot;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ChatbotApp extends JFrame {
    private JTextArea chatArea;
    private SimpleDateFormat timestampFormat = new SimpleDateFormat("HH:mm:ss");

    public ChatbotApp() {
        setupUI();
        displayWelcomeMessage();
    }

    private void setupUI() {
        setTitle("Customer Support Chatbot");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Chat area for displaying conversation
        chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.setLineWrap(true);
        JScrollPane chatScrollPane = new JScrollPane(chatArea);
        add(chatScrollPane, BorderLayout.CENTER);

        // Panel to hold the question buttons
        JPanel buttonPanel = new JPanel(new GridLayout(3, 2, 10, 10));

        // Creating buttons for common questions
        JButton orderStatusButton = new JButton("Order Status");
        JButton returnPolicyButton = new JButton("Return Policy");
        JButton refundPolicyButton = new JButton("Refund Policy");
        JButton shippingInfoButton = new JButton("Shipping Info");
        JButton paymentOptionsButton = new JButton("Payment Options");
        JButton resetButton = new JButton("Reset Chat");

        // Adding action listeners for each button
        orderStatusButton.addActionListener(new QuestionButtonListener("order status"));
        returnPolicyButton.addActionListener(new QuestionButtonListener("return policy"));
        refundPolicyButton.addActionListener(new QuestionButtonListener("refund policy"));
        shippingInfoButton.addActionListener(new QuestionButtonListener("shipping info"));
        paymentOptionsButton.addActionListener(new QuestionButtonListener("payment options"));
        resetButton.addActionListener(e -> resetChat());

        // Add buttons to the panel
        buttonPanel.add(orderStatusButton);
        buttonPanel.add(returnPolicyButton);
        buttonPanel.add(refundPolicyButton);
        buttonPanel.add(shippingInfoButton);
        buttonPanel.add(paymentOptionsButton);
        buttonPanel.add(resetButton);

        // Add the button panel to the frame
        add(buttonPanel, BorderLayout.SOUTH);
    }

    // Display an initial greeting message when the app starts
    private void displayWelcomeMessage() {
        chatArea.append("Bot [" + timestampFormat.format(new Date()) + "]: Hello! Welcome to our support chat. How can I assist you today?\n\n");
    }

    // Inner class to handle button clicks
    private class QuestionButtonListener implements ActionListener {
        private String question;

        public QuestionButtonListener(String question) {
            this.question = question;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            chatArea.append("User [" + timestampFormat.format(new Date()) + "]: " + question + "\n");
            simulateTypingAndRespond(question);
        }
    }

    // Method to simulate bot typing and delay response
    private void simulateTypingAndRespond(String question) {
        // Show "Bot is typing..." message
        String typingMessage = "Bot is typing...\n";
        chatArea.append(typingMessage);

        // Simulate typing delay
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ((Timer) e.getSource()).stop();  // Stop the timer after it fires

                // Remove "Bot is typing..." text by finding and deleting it
                String currentText = chatArea.getText();
                chatArea.setText(currentText.replace(typingMessage, ""));

                // Display the bot's response with timestamp
                chatArea.append("Bot [" + timestampFormat.format(new Date()) + "]: " + Chatbot.respond(question) + "\n\n");
            }
        });
        timer.setRepeats(false);  // Make the timer fire only once
        timer.start();
    }


    // Method to clear chat area and display welcome message again
    private void resetChat() {
        chatArea.setText("");  // Clear chat area
        displayWelcomeMessage();  // Display initial greeting message again
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ChatbotApp chatbotApp = new ChatbotApp();
            chatbotApp.setVisible(true);
        });
    }
}
