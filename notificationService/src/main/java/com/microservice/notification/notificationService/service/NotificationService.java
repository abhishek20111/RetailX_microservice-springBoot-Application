package com.microservice.notification.notificationService.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationService {

    private final JavaMailSender javaMailSender;

    @KafkaListener(topics = "order-placed", groupId = "notificationService")
    public void listen(String orderPlaceEventJson) {
        log.info("Received JSON message from order-placed topic: {}", orderPlaceEventJson);

        // Deserialize JSON to a Map
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Map<String, Object> orderPlaceEvent = objectMapper.readValue(orderPlaceEventJson, new TypeReference<Map<String, Object>>() {});
            String orderNumber = (String) orderPlaceEvent.get("orderNumber");
            String email = (String) orderPlaceEvent.get("email");

            log.info("Deserialized OrderPlaceEvent - Order Number: {}, Email: {}", orderNumber, email);

            // Send email logic here
            sendOrderConfirmationEmail(email, orderNumber);
        } catch (JsonProcessingException e) {
            log.error("Failed to deserialize JSON message", e);
            throw new RuntimeException("Failed to deserialize JSON message", e);
        }
    }

    private void sendOrderConfirmationEmail(String email, String orderNumber) {
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom("springshop@email.com");
            messageHelper.setTo(email);
            messageHelper.setSubject(String.format("Your Order with OrderNumber %s is placed successfully", orderNumber));
            messageHelper.setText(String.format("""
                            Hi Customer,

                            Your order with order number %s is now placed successfully.

                            Best Regards,
                            Spring Shop
                            """,
                    orderNumber));
        };

        try {
            javaMailSender.send(messagePreparator);
            log.info("Order Notification email sent to: {}", email);
            System.out.println("Order Notification email sent to: {}"+ email);
        } catch (MailException e) {
            log.error("Exception occurred when sending mail to: {}", email, e);
            throw new RuntimeException("Exception occurred when sending mail to: " + email, e);
        }
    }
}