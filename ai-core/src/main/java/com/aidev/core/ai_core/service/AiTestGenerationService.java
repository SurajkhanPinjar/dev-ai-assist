package com.aidev.core.ai_core.service;

import com.aidevassist.model.dto.TestGenRequest;
import com.aidevassist.model.dto.TestGenResponse;

public interface AiTestGenerationService {
    TestGenResponse generateTests(TestGenRequest request);
}