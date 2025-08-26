// package: com.aidevassist.junit_generator.service.impl

package com.aidevassist.junit_generator.service.impl;

import com.aidev.core.ai_core.service.AiTestGenerationService;
import com.aidevassist.junit_generator.service.TestGeneratorService;
import com.aidevassist.model.dto.TestGenRequest;
import com.aidevassist.model.dto.TestGenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TestGeneratorServiceImpl implements TestGeneratorService {

    private final AiTestGenerationService aiTestGenerationService;

    @Override
    public TestGenResponse aiGenerateTests(TestGenRequest request) {
        return aiTestGenerationService.generateTests(request);
    }
}