package com.aidev.core.ai_core.controller;

import com.aidev.core.ai_core.dto.PromptRequest;
import com.aidev.core.ai_core.service.AiOptimizerService;
import com.aidevassist.model.dto.OptimizationRequest;
import com.aidevassist.model.dto.OptimizationResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ai")
@RequiredArgsConstructor
@Tag(name = "AI Assist", description = "Endpoints for interacting with local Ollama model")
public class OllamaController {

    private final OllamaChatModel chatModel;

    private final AiOptimizerService aiOptimizerService;

    @Operation(
            summary = "Send a prompt to AI",
            description = "Sends a prompt to the configured Ollama model (e.g., mistral) and returns the AI response."
    )
    @PostMapping("/ask")
    public ResponseEntity<String> ask(@RequestBody PromptRequest request) {
        String response = chatModel.call(request.getPrompt());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/optimize/ai")
    @Operation(summary = "Optimize code with AI",
            description = "Uses AI (Ollama) to suggest improvements.")
    public ResponseEntity<OptimizationResponse> optimizeWithAi(@RequestBody OptimizationRequest request) {
        return ResponseEntity.ok(aiOptimizerService.optimizeWithAi(request));
    }
}