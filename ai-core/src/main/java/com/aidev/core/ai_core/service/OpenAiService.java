package com.aidev.core.ai_core.service;


import com.aidev.core.ai_core.dto.OpenAiRequestDto;
import com.aidev.core.ai_core.dto.OpenAiResponseDto;

public interface OpenAiService {
    OpenAiResponseDto fetchResponse(OpenAiRequestDto requestDto);
}
