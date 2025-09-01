package com.aidev.core.ai_core.rag;

import dev.langchain4j.service.SystemMessage;

public interface RAGService {

    @SystemMessage("""
        You are a helpful assistant that answers questions based on the provided context.
        If the context does not contain the answer, say "I couldn't find the answer in the provided documents."
        """)
    String chat(String query);


    @SystemMessage("""
        You are a helpful assistant that answers questions based on the provided context.
        If the context does not contain the answer, say "I couldn't find the answer in the provided documents."
        """)
    String answer(String query);
}
