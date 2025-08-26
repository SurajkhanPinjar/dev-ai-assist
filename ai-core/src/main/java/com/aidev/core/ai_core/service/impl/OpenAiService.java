//package com.aidev.core.ai_core.service.impl;
//
//import com.aidev.core.ai_core.dto.OpenAiRequestDto;
//import com.aidev.core.ai_core.dto.OpenAiResponseDto;
//import com.aidev.core.ai_core.service.AiService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.*;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Objects;
//
//
//@Service
//@RequiredArgsConstructor
//public class AiServiceImpl1 implements AiService {
//
//    @Value("${openai.api-url}")
//    private String apiUrl;
//
//    @Value("${openai.model}")
//    private String model;
//
//    @Value("${openai.key}")
//    private String apiKey;
//
//    private final RestTemplate restTemplate = new RestTemplate();
//
//    @Override
//    public String ask(String prompt) {
//        Map<String, Object> body = Map.of(
//                "model", model,
//                "messages", new Object[]{
//                        Map.of("role", "user",
//                                "content", prompt)
//                },
//                "temperature", 0.7
//        );
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        headers.setBearerAuth(apiKey);
//
//        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);
//
//        ResponseEntity<Map> response = restTemplate.postForEntity(
//                apiUrl,
//                entity,
//                Map.class
//        );
//        if (response.getBody() != null) {
//            var choices = (java.util.List<Map<String, Object>>) response.getBody().get("choices");
//            if (choices != null && !choices.isEmpty()) {
//                Map<String, Object> message = (Map<String, Object>) choices.get(0).get("message");
//                return (String) message.get("content");
//            }
//        }
//        return "No response from AI";
//    }
//
//}
