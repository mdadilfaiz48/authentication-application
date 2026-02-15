package com.auth.client;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class SendInBlueClient {

    @Value("${brevo.api.key}")
    private String apiKey;

    @Value("${brevo.url}")
    private String sendInBlueUrl;

    @Value("${brevo.sender.email}")
    private String senderEmail;

    @Value("${brevo.sender.name}")
    private String senderName;

    private static final Logger log = LoggerFactory.getLogger(SendInBlueClient.class);

    private final RestTemplate restTemplate = new RestTemplate();
    public void sendOtpEmail(String email, String otp) {
        log.info("SendInBlueClient.sendOtpEmail-------------------------> email: {}, otp: {}", email, otp);

        // Email body content
        Map<String, Object> sender = new HashMap<>();
        sender.put("email", senderEmail);
        sender.put("name", senderName);

        Map<String, String> toUser = new HashMap<>();
        toUser.put("email", email);

        List<Map<String, String>> toList = Collections.singletonList(toUser);

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("sender", sender);
        requestBody.put("to", toList);
        requestBody.put("subject", "Your OTP Code");
        requestBody.put("textContent",
                "Your OTP is: " + otp +
                        "\nValid for 5 minutes.\nDo not share with anyone.");

        // Headers

        HttpHeaders headers = new HttpHeaders();
        headers.set("api-key", apiKey.trim());  // ✅ replace, not append
        log.info("SendInBlueClient.sendOtpEmail------------------------);> Headers: {}", headers);
        headers.setContentType(MediaType.APPLICATION_JSON);

        log.info("SendInBlueClient.sendOtpEmail-------------------------> Request Body: {}", requestBody);
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);

        try {
            ResponseEntity<String> response = restTemplate.exchange(
                    sendInBlueUrl,
                    HttpMethod.POST,
                    entity,
                    String.class
            );

            if (!response.getStatusCode().is2xxSuccessful()) {
                throw new RuntimeException("Failed to send email: " + response.getBody());
            }

        } catch (Exception e) {
            throw new RuntimeException("Brevo email sending failed", e);
        }
      }
}
