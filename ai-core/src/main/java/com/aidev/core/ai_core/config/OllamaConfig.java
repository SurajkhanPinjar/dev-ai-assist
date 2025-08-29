package com.aidev.core.ai_core.config;

import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.ollama.OllamaEmbeddingModel;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.inmemory.InMemoryEmbeddingStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * This class provides the necessary Spring beans for the LangChain4j integration with Ollama.
 * It configures the EmbeddingModel and the EmbeddingStore, making them available for
 * dependency injection throughout the application.
 */
@Configuration
public class OllamaConfig {

    @Value("${spring.ai.ollama.base-url:http://localhost:11434}")
    private String baseUrl;

    @Value("${spring.ai.ollama.model:nomic-embed-text}")
    private String modelName;

    /**
     * Configures and provides an Ollama-based EmbeddingModel bean.
     * This bean is responsible for communicating with a running Ollama server to
     * generate text embeddings.
     *
     * @return The configured OllamaEmbeddingModel.
     */
    @Bean
    public EmbeddingModel embeddingModel() {
        // We use OllamaEmbeddingModel to connect to our local Ollama server.
        return OllamaEmbeddingModel.builder()
                .baseUrl(baseUrl)
                .modelName(modelName)
                .build();
    }

    /**
     * Configures and provides an in-memory EmbeddingStore bean.
     * This store holds the text segments and their corresponding embeddings in RAM.
     *
     * In a production environment, you would typically replace this with a
     * persistent vector database like Chroma, Pinecone, or Weaviate.
     *
     * @return The configured InMemoryEmbeddingStore.
     */
    @Bean
    public EmbeddingStore<TextSegment> embeddingStore() {
        // InMemoryEmbeddingStore is a simple, in-memory implementation for quick testing.
        return new InMemoryEmbeddingStore<>();
    }
}
