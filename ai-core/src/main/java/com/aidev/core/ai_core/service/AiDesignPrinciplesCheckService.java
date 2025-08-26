package com.aidev.core.ai_core.service;

import com.aidevassist.model.dto.DesignPrincipleRequest;
import com.aidevassist.model.dto.DesignPrincipleResponse;

public interface AiDesignPrinciplesCheckService {
    DesignPrincipleResponse checkDesignPrinciples(DesignPrincipleRequest request);
}
