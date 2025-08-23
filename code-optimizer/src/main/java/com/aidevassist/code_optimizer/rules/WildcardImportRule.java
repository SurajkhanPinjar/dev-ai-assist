package com.aidevassist.code_optimizer.rules;

import com.aidevassist.code_optimizer.dto.OptimizationResult;
import org.springframework.stereotype.Component;

@Component
public class WildcardImportRule implements OptimizationRule{

    @Override
    public OptimizationResult apply(String code) {
        if (code.contains("import java.util.*;")) {
            String newCode = code.replace("import java.util.*;",
                    "import java.util.List;\nimport java.util.Map;");
            return new OptimizationResult(newCode,
                    "Avoid wildcard imports. Replace with specific imports.");
        }
        return new OptimizationResult(code, null);
    }
}
