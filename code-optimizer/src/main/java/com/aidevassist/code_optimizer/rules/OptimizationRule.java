package com.aidevassist.code_optimizer.rules;

import com.aidevassist.code_optimizer.dto.OptimizationResult;

public interface OptimizationRule {
    OptimizationResult apply(String code);
}
