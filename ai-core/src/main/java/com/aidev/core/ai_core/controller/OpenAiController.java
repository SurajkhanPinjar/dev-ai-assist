package com.aidev.core.ai_core.controller;

import com.aidev.core.ai_core.dto.OpenAiRequestDto;
import com.aidev.core.ai_core.dto.OpenAiResponseDto;
import com.aidev.core.ai_core.service.OpenAiService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ai")
@RequiredArgsConstructor
public class OpenAiController {

    @Autowired
    private final OpenAiService openAiService;

    @PostMapping("/chat")
    public OpenAiResponseDto getResponse(@RequestBody OpenAiRequestDto requestDto) {
        return openAiService.fetchResponse(requestDto);
    }
}