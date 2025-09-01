//package com.aidev.core.ai_core.controller;
//
//import dev.langchain4j.data.document.Metadata;
//import dev.langchain4j.data.segment.TextSegment;
//import dev.langchain4j.model.embedding.EmbeddingModel;
//import dev.langchain4j.store.embedding.EmbeddingSearchRequest;
//import dev.langchain4j.store.embedding.weaviate.WeaviateEmbeddingStore;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.Map;
//
//@RestController
//@RequestMapping("/api/weaviate")
//public class WeaviateController {
//
//
//    private final WeaviateEmbeddingStore weaviateEmbeddingStore;
//    private final EmbeddingModel embeddingModel;
//
//    public WeaviateController(@Qualifier("WeaviatePrimaryStore") WeaviateEmbeddingStore weaviateEmbeddingStore, EmbeddingModel embeddingModel) {
//        this.weaviateEmbeddingStore = weaviateEmbeddingStore;
//        this.embeddingModel = embeddingModel;
//    }
//
//    /**
//     * Add a document to Weaviate
//     */
//    @PostMapping("/add")
//    public Map<String, String> addDocument(@RequestBody Map<String, String> request) {
//        String text = request.get("text");
//        if (text == null || text.isEmpty()) {
//            return Map.of("error", "Text is required");
//        }
//
//
//        Map<String, Object> md = Map.of(
//                "source", "swagger"
//        );
//        // Or with metadata
//        Metadata metadata = new Metadata(md);
//        TextSegment segment = new TextSegment(text, metadata);
//
//        var embedding = embeddingModel.embed(text).content();
//
//        // Add to Weaviate
//        weaviateEmbeddingStore.add(embedding, segment);
//
//        return Map.of("status", "Document added successfully");
//    }
//
//    /**
//     * Query documents from Weaviate
//     */
//    @PostMapping("/query")
//    public Map<String, Object> query(@RequestBody Map<String, String> request) {
//        String queryText = request.get("query");
//        if (queryText == null || queryText.isEmpty()) {
//            return Map.of("error", "Query is required");
//        }
//
//        // 1. Generate embedding for the query text
//        var queryEmbedding = embeddingModel.embed(queryText).content();
//
//        // 2. Build a search request
//
//        EmbeddingSearchRequest embReq = EmbeddingSearchRequest.builder()
//                .queryEmbedding(queryEmbedding)
//                .maxResults(5)
//                .minScore(0.0) // optional, can omit if not needed
//                .build();
//
//        // 3. Perform search
//        var results = weaviateEmbeddingStore.search(embReq); // top 5 results
//
//        return Map.of(
//                "query", queryText,
//                "results", results
//        );
//    }
//
//    /**
//     * Delete documents by IDs
//     */
//    @DeleteMapping("/delete")
//    public Map<String, String> deleteByIds(@RequestBody List<String> ids) {
//        if (ids == null || ids.isEmpty()) {
//            return Map.of("error", "IDs are required");
//        }
//
//        weaviateEmbeddingStore.removeAll(ids);
//        return Map.of("status", "Documents deleted successfully");
//    }
//
//    /**
//     * Delete all documents in the class
//     */
//    @DeleteMapping("/delete-all")
//    public Map<String, String> deleteAll() {
//        weaviateEmbeddingStore.removeAll();
//        return Map.of("status", "All documents deleted successfully");
//    }
//
//}