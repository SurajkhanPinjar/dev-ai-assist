package com.aidev.core.ai_core.service.impl;

import com.aidev.core.ai_core.service.AiOptimizerService;
import com.aidevassist.model.dto.OptimizationRequest;
import com.aidevassist.model.dto.OptimizationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AiOptimizerServiceImpl implements AiOptimizerService {

    private final OllamaChatModel chatModel;

    @Override
    public OptimizationResponse optimizeWithAi(OptimizationRequest request) {
        String prompt = """
            You are a senior Java code reviewer.
            Analyze this Java code and suggest improvements and optimizations.
            Return suggestions in concise bullet points.

            Code:                
            """ + request.getSourceCode();

        String chatResponse = chatModel.call(prompt);
        return new OptimizationResponse(request.getSourceCode(), List.of(chatResponse));
    }
}
