package com.aidevassist.code_optimizer.rules;

import com.aidevassist.code_optimizer.dto.OptimizationResult;
import org.springframework.stereotype.Component;

@Component
public class SysOutRule implements OptimizationRule {

    @Override
    public OptimizationResult apply(String code) {
        if (code.contains("System.out.println")) {
            String newCode = code.replace("System.out.println", "logger.info");
            return new OptimizationResult(newCode,
                    "Use a Logger instead of System.out.println.");
        }
        return new OptimizationResult(code, null);
    }
}
