package com.aidev.core.ai_core.service.impl;

import com.aidev.core.ai_core.service.AiDesignPrinciplesCheckService;
import com.aidevassist.model.dto.DesignPrincipleRequest;
import com.aidevassist.model.dto.DesignPrincipleResponse;
import com.aidevassist.model.dto.DesignPrincipleViolation;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AiDesignPrinciplesCheckServiceImpl implements AiDesignPrinciplesCheckService {

    private final OllamaChatModel chatModel;

    @Override
    public DesignPrincipleResponse checkDesignPrinciples(DesignPrincipleRequest request) {
        String prompt = """
                You are a senior Java design principle checker.
                Your task is to analyze the given Java code and detect violations of the SOLID principles 
                (Single Responsibility, Open/Closed, Liskov Substitution, Interface Segregation, Dependency Inversion).
                
                Rules:
                - Respond ONLY in valid JSON.
                - Do NOT include any text outside JSON.
                - If no violations are found, return an empty array for "violations".
                - Keep "lineSnippet" short (1–2 lines max).
                
                Return format:
                {
                  "violations": [
                    {
                      "principle": "SRP",
                      "message": "Class violates SRP by handling both persistence and business logic.",
                      "lineSnippet": "public void saveAndProcessOrder(...)"
                    }
                  ],
                  "summary": "Overall summary of detected issues or 'No violations found'.",
                  "suggestions": [
                    "Extract persistence logic into Repository layer.",
                    "Keep Service class focused on business workflow."
                  ]
                }
                
                Code:
                """ + request.getCode();

        String response = chatModel.call(prompt);

        // For now: wrap raw AI response inside summary (later parse properly)
        return DesignPrincipleResponse.builder()
                .violations(List.of(
                        DesignPrincipleViolation.builder()
                                .principle("RAW_AI")
                                .message(response)
                                .lineSnippet(null)
                                .build()
                ))
                .summary("AI raw response, parsing pending")
                .suggestions(List.of("Parse JSON response in next iteration"))
                .build();
    }
}