//package com.aidev.core.ai_core.service.impl;
//
//import com.aidev.core.ai_core.service.AiOptimizerService;
//import com.aidevassist.model.dto.OptimizationRequest;
//import com.aidevassist.model.dto.OptimizationResponse;
//import lombok.RequiredArgsConstructor;
//import org.springframework.ai.ollama.OllamaChatModel;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//public class AiOptimizerServiceImpl implements AiOptimizerService {
//
//    private final OllamaChatModel chatModel;
//
//    @Override
//    public OptimizationResponse optimizeWithAi(OptimizationRequest request) {
//        String prompt = """
//                You are a senior Java code reviewer.
//                Analyze this Java code and suggest improvements and optimizations.
//                Return only concise bullet points with suggestions.
//                Do NOT include any code, examples, or explanations—just the bullet points.
//
//                Code:
//                """ + request.getSourceCode();
//        String promptForCode = """
//                You are a senior Java code reviewer and optimizer.
//                Rewrite the following Java code in an optimized and clean form.
//                Only return the full Java code, wrapped in a ```java code block```.
//                Do not include any explanations or suggestions.
//
//                Code:
//                """ + request.getSourceCode();
//
//        String codeResponse = chatModel.call(promptForCode);
//        String chatResponse = chatModel.call(prompt);
//
//        return new OptimizationResponse(codeResponse, List.of(chatResponse));
//    }
//}
