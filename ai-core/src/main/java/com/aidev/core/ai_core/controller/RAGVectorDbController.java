//package com.aidev.core.ai_core.controller;
//
//import com.aidev.core.ai_core.rag.RAGService;
//import dev.langchain4j.data.document.Document;
//import dev.langchain4j.data.segment.TextSegment;
//import dev.langchain4j.model.embedding.EmbeddingModel;
//import dev.langchain4j.store.embedding.EmbeddingStore;
//import dev.langchain4j.store.embedding.weaviate.WeaviateEmbeddingStore;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.Map;
//
//@RestController
//@RequestMapping("/api/rag")
//public class RAGVectorDbController {
//
//    private final RAGService ragService;
//    private final EmbeddingModel embeddingModel;
//    private final EmbeddingStore<TextSegment> embeddingStore;
//
//    private final WeaviateEmbeddingStore weaviateEmbeddingStore;
//
//    @Autowired
//    public RAGVectorDbController(
//            RAGService ragService,
//            EmbeddingModel embeddingModel,
//            EmbeddingStore<TextSegment> embeddingStore,
//            @Qualifier("WeaviatePrimaryStore") WeaviateEmbeddingStore weaviateEmbeddingStore) {
//        this.ragService = ragService;
//        this.embeddingModel = embeddingModel;
//        this.embeddingStore = embeddingStore;
//        this.weaviateEmbeddingStore = weaviateEmbeddingStore;
//    }
//
//    /**
//     * Add a document to the embedding store & Weaviate
//     */
//    @PostMapping("/add-document")
//    public Map<String, String> addDocument(@RequestBody Map<String, String> request) {
//        String text = request.get("text");
//        if (text == null || text.isEmpty()) {
//            return Map.of("error", "Text is required");
//        }
//
//        // Create a Document and segment
//        Document doc = new Document(text);
//        TextSegment segment = doc.toTextSegment();
//
//        // Generate embedding
//        var embedding = embeddingModel.embed(text).content();
//
//        // Add to in-memory store
//        embeddingStore.add(embedding, segment);
//
//        // Add to Weaviate
//        weaviateEmbeddingStore.add(embedding, segment);
//
//        return Map.of("status", "Document added successfully");
//    }
//
//    /**
//     * Query documents via RAG
//     */
//    @PostMapping("/query")
//    public Map<String, String> query(@RequestBody Map<String, String> request) {
//        String question = request.get("question");
//        if (question == null || question.isEmpty()) {
//            return Map.of("error", "Question is required");
//        }
//
//        String answer = ragService.answer(question);
//        return Map.of(
//                "question", question,
//                "answer", answer
//        );
//    }
//
//}