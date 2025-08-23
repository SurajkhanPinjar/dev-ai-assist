package com.aidevassist.code_optimizer.service.impl;

import com.aidevassist.code_optimizer.dto.CodeRequest;
import com.aidevassist.code_optimizer.dto.CodeResponse;
import com.aidevassist.code_optimizer.dto.OptimizationRequest;
import com.aidevassist.code_optimizer.dto.OptimizationResponse;
import com.aidevassist.code_optimizer.rules.OptimizationRule;
import com.aidevassist.code_optimizer.service.OptimizerService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class OptimizerServiceImpl implements OptimizerService {

    private static final Logger logger = LoggerFactory.getLogger(OptimizerServiceImpl.class);

    private final List<OptimizationRule> rules;

    @Override
    public OptimizationResponse optimizeCode(OptimizationRequest request) {
        String code = request.getSourceCode();
        List<String> suggestions = new ArrayList<>();

        for (OptimizationRule rule : rules) {
            var result = rule.apply(code);
            code = result.getUpdatedCode();
            if(result.getSuggestion() != null) {
                suggestions.add(result.getSuggestion());
            }
        }
        return new OptimizationResponse(code, suggestions);
    }

    @Override
    public CodeResponse optimize(CodeRequest request) {
        OptimizationResponse response = optimizeCode(new OptimizationRequest(request.getCode()));
        return response.toCodeResponse();
    }
}
