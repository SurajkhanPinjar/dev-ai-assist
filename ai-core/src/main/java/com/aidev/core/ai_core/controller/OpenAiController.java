//package com.aidev.core.ai_core.controller;
//
//import com.aidev.core.ai_core.dto.PromptRequest;
//import com.aidev.core.ai_core.service.AiService;
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.tags.Tag;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.Map;
//
//@RestController
//@RequestMapping("/api/ai")
//@RequiredArgsConstructor
//@Tag(name = "AI Assist", description = "Endpoints for interacting with local Ollama model")
//public class AiController {
//
//
//    private final AiService aiService;
//
//    @Operation(
//            summary = "Send a prompt to AI",
//            description = "Sends a prompt to the configured Ollama model and returns the AI response."
//    )
//    @PostMapping("/ask")
//    public ResponseEntity<String> ask(@RequestBody PromptRequest request) {
//        String prompt = request.getPrompt();
//        String response = aiService.ask(prompt);
//        return ResponseEntity.ok(response);
//    }
//}