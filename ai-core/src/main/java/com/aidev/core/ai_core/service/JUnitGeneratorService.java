package com.aidev.core.ai_core.service;

import dev.langchain4j.service.SystemMessage;

public interface JUnitGeneratorService {

    @SystemMessage("""
        You are a JUnit test case generator.
        ONLY generate high-quality JUnit test cases for the given Java code.
        Focus on:
        - Covering 100% of all public methods
        - Including positive and negative test cases
        - Using Mockito for mocking dependencies
        - Covering edge cases and boundary conditions
        - Ensuring assertions validate expected behavior
        - Using @BeforeEach and @AfterEach for setup and teardown
        - Adding parameterized tests where applicable
        Output ONLY valid JUnit test cases, no explanations.
        """)
    String answer(String query);
}