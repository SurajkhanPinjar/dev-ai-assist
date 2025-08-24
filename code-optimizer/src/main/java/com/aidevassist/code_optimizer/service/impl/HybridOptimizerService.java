package com.aidevassist.code_optimizer.service.impl;

import com.aidev.core.ai_core.service.AiOptimizerService;
import com.aidevassist.code_optimizer.service.OptimizerService;

import com.aidevassist.model.dto.OptimizationRequest;
import com.aidevassist.model.dto.OptimizationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class HybridOptimizerService {

    private final AiOptimizerService aiOptimizerService;

    private final OptimizerService optimizerService;

    public OptimizationResponse optimize(OptimizationRequest request) {
        //  Step - 1 : Apply rule-based optimization
        OptimizationResponse optimizedResponse = optimizerService.optimizeCode(request);

        // Step 2: Send rule-optimized code to AI
        OptimizationRequest aiRequest = new OptimizationRequest(optimizedResponse.getOptimizedCode());
        OptimizationResponse aiResponse = aiOptimizerService.optimizeWithAi(aiRequest);

        List<String> combinedSuggestions = new ArrayList<>();
        combinedSuggestions.addAll(optimizedResponse.getSuggestions());
        combinedSuggestions.addAll(aiResponse.getSuggestions());

        return new OptimizationResponse(optimizedResponse.getOptimizedCode(), combinedSuggestions);
    }
}
