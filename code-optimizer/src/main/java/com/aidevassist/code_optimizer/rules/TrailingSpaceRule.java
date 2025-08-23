package com.aidevassist.code_optimizer.rules;

import com.aidevassist.code_optimizer.dto.OptimizationResult;
import org.springframework.stereotype.Component;

@Component
public class TrailingSpaceRule implements OptimizationRule {

    @Override
    public OptimizationResult apply(String code) {
        String newCode = code.replaceAll("[ \t]+$", "");
        if (!newCode.equals(code)) {
            return new OptimizationResult(newCode,
                    "Remove unnecessary trailing spaces.");
        }
        return new OptimizationResult(code, null);
    }
}
