package com.aidevassist.code_optimizer.service;

import com.aidevassist.model.dto.OptimizationRequest;
import com.aidevassist.model.dto.OptimizationResponse;

public interface OptimizerService {
    OptimizationResponse optimizeCode(OptimizationRequest request);
}
