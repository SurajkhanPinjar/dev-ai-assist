package com.aidevassist.design_principle_checks.service.impl;

import com.aidev.core.ai_core.service.AiDesignPrinciplesCheckService;
import com.aidevassist.design_principle_checks.service.DesignPrincipleService;
import com.aidevassist.model.dto.DesignPrincipleRequest;
import com.aidevassist.model.dto.DesignPrincipleResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DesignPrincipleServiceImpl implements DesignPrincipleService {

    private final AiDesignPrinciplesCheckService aiDesignPrinciplesCheckService;

    @Override
    public DesignPrincipleResponse aiAnalyzePrinciples(DesignPrincipleRequest request) {
        return aiDesignPrinciplesCheckService.checkDesignPrinciples(request);
    }
}