package com.aidev.core.ai_core.service;

import com.aidevassist.model.dto.OptimizationRequest;
import com.aidevassist.model.dto.OptimizationResponse;

public interface AiOptimizerService {
    OptimizationResponse optimizeWithAi (OptimizationRequest request);
}
