package com.aidevassist.code_optimizer.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OptimizationResult {
    private final String updatedCode;
    private final String suggestion;
}
