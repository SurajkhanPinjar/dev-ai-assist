package com.aidevassist.junit_generator.service;


import com.aidevassist.model.dto.TestGenRequest;
import com.aidevassist.model.dto.TestGenResponse;

public interface TestGeneratorService {
    TestGenResponse aiGenerateTests(TestGenRequest request);
}