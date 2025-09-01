package com.aidev.core.ai_core.service.impl;

import com.aidev.core.ai_core.service.CodeOptimizerService;
import com.aidev.core.ai_core.service.CodeReviewerService;
import com.aidev.core.ai_core.service.JUnitGeneratorService;
import com.aidev.core.ai_core.service.JavaDocsService;
import dev.langchain4j.data.document.Metadata;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.ollama.OllamaEmbeddingModel;
import dev.langchain4j.store.embedding.weaviate.WeaviateEmbeddingStore;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

@Service
public class WeaviateFileServiceImpl {

    private final OllamaEmbeddingModel embeddingModel;
    private final WeaviateEmbeddingStore javaDocsStore;
    private final WeaviateEmbeddingStore codeOptimizerStore;
    private final WeaviateEmbeddingStore codeReviewerStore;
    private final WeaviateEmbeddingStore junitTestStore;

    private final JavaDocsService javaDocsService;
    private final CodeOptimizerService codeOptimizerService;
    private final CodeReviewerService codeReviewerService;
    private final JUnitGeneratorService junitGeneratorService;

    public WeaviateFileServiceImpl(
            OllamaEmbeddingModel embeddingModel,
            @Qualifier("javaDocsStore") WeaviateEmbeddingStore javaDocsStore,
            @Qualifier("codeOptimizerStore") WeaviateEmbeddingStore codeOptimizerStore,
            @Qualifier("codeReviewerStore") WeaviateEmbeddingStore codeReviewerStore,
            @Qualifier("junitTestStore") WeaviateEmbeddingStore junitTestStore,
            JavaDocsService javaDocsService,
            CodeOptimizerService codeOptimizerService,
            CodeReviewerService codeReviewerService,
            JUnitGeneratorService junitGeneratorService
    ) {
        this.embeddingModel = embeddingModel;
        this.javaDocsStore = javaDocsStore;
        this.codeOptimizerStore = codeOptimizerStore;
        this.codeReviewerStore = codeReviewerStore;
        this.junitTestStore = junitTestStore;
        this.javaDocsService = javaDocsService;
        this.codeOptimizerService = codeOptimizerService;
        this.codeReviewerService = codeReviewerService;
        this.junitGeneratorService = junitGeneratorService;
    }



    public Map<String, String> addFileWeaviate(String fileName, String type) {
        try {
            URL resource = getClass().getClassLoader().getResource("docs/" + fileName);
            if (resource == null) return Map.of("error", "File not found");

            Path path = Paths.get(resource.toURI());
            String text = Files.readString(path);

            Map<String, Object> md = Map.of(
                    "source", "local txt files"
            );
            // Or with metadata
            Metadata metadata = new Metadata(md);
            TextSegment segment = new TextSegment(text, metadata);

            var embedding = embeddingModel.embed(text).content();

            // Choose store based on type
            switch (type.toLowerCase()) {
                case "code-optimizer" -> codeOptimizerStore.add(embedding, segment);
                case "junit-generator" -> junitTestStore.add(embedding, segment);
                case "code-reviewer" -> codeReviewerStore.add(embedding, segment);
                case "java-docs" -> javaDocsStore.add(embedding, segment);
                default -> throw new IllegalArgumentException("Invalid type");
            }

            return Map.of("status", "Document added", "file", fileName, "type", type);
        } catch (Exception e) {
            return Map.of("error", e.getMessage());
        }
    }

    // ---------- Query Documents ----------
    @PostMapping("/query")
    public Map<String, String> query(@RequestParam String type, Map<String, String> request) {
        try {
            String question = request.get("question");
            if (question == null || question.isBlank()) {
                return Map.of("error", "Question is required");
            }

            String answer;
            switch (type.toLowerCase()) {
                case "java-docs" -> answer = javaDocsService.answer(question);
                case "code-optimizer" -> answer = codeOptimizerService.answer(question);
                case "code-reviewer" -> answer = codeReviewerService.answer(question);
                case "junit-test" -> answer = junitGeneratorService.answer(question);
                default -> {
                    return Map.of("error", "Invalid type");
                }
            }

            return Map.of(
                    "question", question,
                    "answer", answer,
                    "type", type
            );
        } catch (Exception e) {
            return Map.of("error", e.getMessage());
        }
    }

    // ---------- Clear All Data ----------
    public Map<String, String> clearStore(String type) {
        try {
            switch (type.toLowerCase()) {
                case "java-docs" -> javaDocsStore.removeAll();
                case "code-optimizer" -> codeOptimizerStore.removeAll();
                case "code-reviewer" -> codeReviewerStore.removeAll();
                case "junit-test" -> junitTestStore.removeAll();
                default -> {
                    return Map.of("error", "Invalid type");
                }
            }
            return Map.of("status", "Data cleared successfully", "type", type);
        } catch (Exception e) {
            return Map.of("error", e.getMessage());
        }
    }


}
