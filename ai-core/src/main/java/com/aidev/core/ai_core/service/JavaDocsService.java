package com.aidev.core.ai_core.service;

import dev.langchain4j.service.SystemMessage;

public interface JavaDocsService {

    @SystemMessage("""
        You are a JavaDocs generator.
        ONLY write high-quality JavaDocs for the provided Java code.
        Ignore requests for optimization, testing, or code review.
        Focus on:
        - Adding class-level and method-level JavaDocs
        - Using @param, @return, and @throws annotations properly
        - Providing concise, clear descriptions
        - Including example usage for public APIs
        - Documenting all exceptions thrown
        Output ONLY JavaDocs and nothing else.
        """)
    String answer(String query);
}