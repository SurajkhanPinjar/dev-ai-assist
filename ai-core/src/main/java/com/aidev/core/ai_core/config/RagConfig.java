package com.aidev.core.ai_core.config;

import com.aidev.core.ai_core.rag.RAGService;
import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.loader.FileSystemDocumentLoader;
import dev.langchain4j.data.document.parser.TextDocumentParser;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.ollama.OllamaChatModel;
import dev.langchain4j.model.ollama.OllamaEmbeddingModel;
import dev.langchain4j.rag.content.retriever.ContentRetriever;
import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.inmemory.InMemoryEmbeddingStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class RagConfig {

    @Value("${spring.ai.ollama.base-url:http://localhost:11434}")
    private String baseUrl;

    @Value("${spring.ai.ollama.model:nomic-embed-text}")
    private String modelName;

    @Bean
    public OllamaChatModel chatModel() {
        return OllamaChatModel.builder()
                .baseUrl(baseUrl)    // 👈 now using your @Value injected property
                .modelName("llama3") // 👈 or whichever Ollama chat model you want
                .build();
    }

    @Bean
    public EmbeddingModel embeddingModel() {
        // We use OllamaEmbeddingModel to connect to our local Ollama server.
        return OllamaEmbeddingModel.builder()
                .baseUrl(baseUrl)
                .modelName(modelName)
                .build();
    }

    @Bean
    public EmbeddingStore<TextSegment> embeddingStore() {
        // InMemoryEmbeddingStore is a simple, in-memory implementation for quick testing.
        return new InMemoryEmbeddingStore<>();
    }


    @Bean
    public ContentRetriever contentRetriever(EmbeddingStore<TextSegment> embeddingStore, EmbeddingModel embeddingModel) {
        Document document = loadDocument("my-document.txt");

        embeddingStore.add(embeddingModel.embed(document.text()).content(), document.toTextSegment());

        // Return a retriever to fetch relevant content
        return new EmbeddingStoreContentRetriever(embeddingStore, embeddingModel);
    }

    private Document loadDocument(String fileName) {
        try {
            URL resource = getClass().getClassLoader().getResource("docs/" + fileName);
            if (resource == null) {
                throw new IllegalArgumentException("Document not found: " + fileName);
            }
            Path filePath = Paths.get(resource.toURI());
            return FileSystemDocumentLoader.loadDocument(filePath, new TextDocumentParser());
        } catch (URISyntaxException e) {
            throw new RuntimeException("Failed to load document", e);
        }
    }

    @Bean
    public RAGService ragService(OllamaChatModel chatModel, ContentRetriever contentRetriever) {
        return AiServices.builder(RAGService.class)
                .chatLanguageModel(chatModel)
                .contentRetriever(contentRetriever)
                .build();
    }

}
