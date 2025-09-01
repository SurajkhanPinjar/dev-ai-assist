package com.aidev.core.ai_core.config;

import com.aidev.core.ai_core.rag.RAGService;
import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.loader.FileSystemDocumentLoader;
import dev.langchain4j.data.document.parser.TextDocumentParser;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.ollama.OllamaChatModel;
import dev.langchain4j.model.ollama.OllamaEmbeddingModel;
import dev.langchain4j.rag.content.retriever.ContentRetriever;
import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.store.embedding.weaviate.WeaviateEmbeddingStore;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class RagVectorDbConfig {

    @Bean
    public OllamaChatModel chatModel() {
        return OllamaChatModel.builder()
                .baseUrl("http://localhost:11434")
                .modelName("mistral")
                .build();
    }

    @Bean
    public OllamaEmbeddingModel embeddingModel() {
        return OllamaEmbeddingModel.builder()
                .baseUrl("http://localhost:11434")
                .modelName("nomic-embed-text")
                .build();
    }

    @Bean
    @Qualifier("WeaviatePrimaryStore")
    public WeaviateEmbeddingStore weaviateEmbeddingStore() {
        return WeaviateEmbeddingStore.builder()
                .scheme("http")
                .host("localhost")
                .port(8080)
                .objectClass("Documents")
                .avoidDups(true)
                .build();
    }

    private Document loadDocument(String fileName) {
        try {
            URL resource = getClass().getClassLoader().getResource("docs/" + fileName);
            Path filePath = Paths.get(resource.toURI());
            return FileSystemDocumentLoader.loadDocument(filePath, new TextDocumentParser());
        } catch (Exception e) {
            throw new RuntimeException("Failed to load document", e);
        }
    }

    @Bean
    public ContentRetriever contentRetriever(WeaviateEmbeddingStore weaviateEmbeddingStore,
                                             EmbeddingModel embeddingModel) {
        // Retrieval will query Weaviate directly
        return new EmbeddingStoreContentRetriever(weaviateEmbeddingStore, embeddingModel);
    }

    @Bean
    public RAGService ragService(OllamaChatModel chatModel, ContentRetriever contentRetriever) {
        return AiServices.builder(RAGService.class)
                .chatLanguageModel(chatModel)
                .contentRetriever(contentRetriever)
                .build();
    }


}