package com.aidev.core.ai_core.service;

import com.aidev.core.ai_core.dto.OpenAiRequestDto;
import com.aidev.core.ai_core.dto.OpenAiResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class OpenAiServiceImpl implements OpenAiService{

    @Value("${openai.api-url}")
    private String apiUrl;

    @Value("${openai.model}")
    private String model;

    @Value("${openai.key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public OpenAiResponseDto fetchResponse(OpenAiRequestDto requestDto) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(apiKey);
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("model", model);
        requestBody.put("messages", List.of(Map.of("role", "user", "content", requestDto.getPrompt())));

        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<Map> response = restTemplate.exchange(apiUrl, HttpMethod.POST, requestEntity, Map.class);
        String result = ((Map)((List) response.getBody().get("choices")).get(0)).get("message").toString();

        OpenAiResponseDto dto = new OpenAiResponseDto();
        dto.setResult(result);
        return dto;

    }
}
