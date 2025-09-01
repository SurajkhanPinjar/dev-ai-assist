package com.aidev.core.ai_core.config;

import com.aidev.core.ai_core.service.CodeOptimizerService;
import com.aidev.core.ai_core.service.CodeReviewerService;
import com.aidev.core.ai_core.service.JUnitGeneratorService;
import com.aidev.core.ai_core.service.JavaDocsService;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.ollama.OllamaChatModel;
import dev.langchain4j.rag.content.retriever.ContentRetriever;
import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.store.embedding.weaviate.WeaviateEmbeddingStore;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DevAiAssistConfig {

    // ------------------- WEAVIATE STORES -------------------

    @Bean
    @Qualifier("javaDocsStore")
    public WeaviateEmbeddingStore javaDocsStore() {
        return WeaviateEmbeddingStore.builder()
                .scheme("http")
                .host("localhost")
                .port(8080)
                .objectClass("JavaDocs")
                .avoidDups(true)
                .build();
    }

    @Bean
    @Qualifier("codeOptimizerStore")
    public WeaviateEmbeddingStore codeOptimizerStore() {
        return WeaviateEmbeddingStore.builder()
                .scheme("http")
                .host("localhost")
                .port(8080)
                .objectClass("CodeOptimizer")
                .avoidDups(true)
                .build();
    }

    @Bean
    @Qualifier("codeReviewerStore")
    public WeaviateEmbeddingStore codeReviewerStore() {
        return WeaviateEmbeddingStore.builder()
                .scheme("http")
                .host("localhost")
                .port(8080)
                .objectClass("CodeReviewer")
                .avoidDups(true)
                .build();
    }

    @Bean
    @Qualifier("junitTestStore")
    public WeaviateEmbeddingStore junitTestStore() {
        return WeaviateEmbeddingStore.builder()
                .scheme("http")
                .host("localhost")
                .port(8080)
                .objectClass("JUnitTestCaseGenerator")
                .avoidDups(true)
                .build();
    }

    // ------------------- CONTENT RETRIEVERS -------------------

    @Bean
    @Qualifier("javaDocsRetriever")
    public ContentRetriever javaDocsRetriever(
            @Qualifier("javaDocsStore") WeaviateEmbeddingStore store,
            EmbeddingModel embeddingModel) {
        return new EmbeddingStoreContentRetriever(store, embeddingModel);
    }

    @Bean
    @Qualifier("codeOptimizerRetriever")
    public ContentRetriever codeOptimizerRetriever(
            @Qualifier("codeOptimizerStore") WeaviateEmbeddingStore store,
            EmbeddingModel embeddingModel) {
        return new EmbeddingStoreContentRetriever(store, embeddingModel);
    }

    @Bean
    @Qualifier("codeReviewerRetriever")
    public ContentRetriever codeReviewerRetriever(
            @Qualifier("codeReviewerStore") WeaviateEmbeddingStore store,
            EmbeddingModel embeddingModel) {
        return new EmbeddingStoreContentRetriever(store, embeddingModel);
    }

    @Bean
    @Qualifier("junitTestRetriever")
    public ContentRetriever junitTestRetriever(
            @Qualifier("junitTestStore") WeaviateEmbeddingStore store,
            EmbeddingModel embeddingModel) {
        return new EmbeddingStoreContentRetriever(store, embeddingModel);
    }

    // ------------------- AI SERVICES -------------------

    @Bean
    public JavaDocsService javaDocsRag(OllamaChatModel chatModel,
                                       @Qualifier("javaDocsRetriever") ContentRetriever retriever) {
        return AiServices.builder(JavaDocsService.class)
                .chatLanguageModel(chatModel)
                .contentRetriever(retriever)
                .build();
    }

    @Bean
    public CodeOptimizerService codeOptimizerRag(OllamaChatModel chatModel,
                                                 @Qualifier("codeOptimizerRetriever") ContentRetriever retriever) {
        return AiServices.builder(CodeOptimizerService.class)
                .chatLanguageModel(chatModel)
                .contentRetriever(retriever)
                .build();
    }

    @Bean
    public CodeReviewerService codeReviewerRag(OllamaChatModel chatModel,
                                               @Qualifier("codeReviewerRetriever") ContentRetriever retriever) {
        return AiServices.builder(CodeReviewerService.class)
                .chatLanguageModel(chatModel)
                .contentRetriever(retriever)
                .build();
    }

    @Bean
    public JUnitGeneratorService junitRag(OllamaChatModel chatModel,
                                          @Qualifier("junitTestRetriever") ContentRetriever retriever) {
        return AiServices.builder(JUnitGeneratorService.class)
                .chatLanguageModel(chatModel)
                .contentRetriever(retriever)
                .build();
    }
}