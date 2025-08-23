package com.aidevassist.code_optimizer.service;

import com.aidevassist.model.dto.CodeRequest;
import com.aidevassist.model.dto.CodeResponse;
import com.aidevassist.model.dto.OptimizationRequest;
import com.aidevassist.model.dto.OptimizationResponse;

public interface OptimizerService {
    OptimizationResponse optimizeCode(OptimizationRequest request);
    CodeResponse optimize(CodeRequest request);
}
