//package com.aidev.core.ai_core.controller;
//
//import dev.langchain4j.data.embedding.Embedding;
//import dev.langchain4j.data.segment.TextSegment;
//import dev.langchain4j.model.embedding.EmbeddingModel;
//import dev.langchain4j.store.embedding.EmbeddingMatch;
//import dev.langchain4j.store.embedding.EmbeddingStore;
//import lombok.RequiredArgsConstructor;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.Map;
//import java.util.stream.Collectors;
//
//@RestController
//@RequestMapping("/api/embeddings")
//@RequiredArgsConstructor
//public class EmbeddingController {
//
//    private final EmbeddingModel embeddingModel;
//    private final EmbeddingStore<TextSegment> embeddingStore;
//
//    /**
//     * Handles a POST request to store a text embedding.
//     * The method receives a text string, generates its embedding, and saves both
//     * the embedding and the original text in the configured embedding store.
//     *
//     * @param text The plain text content from the request body.
//     * @return A confirmation message indicating success.
//     */
//    @PostMapping("/store")
//    public String storeEmbedding(@RequestBody String text) {
//        // 1. Create a TextSegment from the input text.
//        // This is a wrapper that keeps the original text connected to the embedding.
//        TextSegment textSegment = TextSegment.from(text);
//
//        // 2. Generate the embedding for the given text segment.
//        // The .content() method extracts the actual Embedding object.
//        Embedding embedding = embeddingModel.embed(textSegment).content();
//
//        // 3. Store the generated embedding and the original text segment
//        // in the vector database or store.
//        embeddingStore.add(embedding, textSegment);
//
//        // 4. Return a simple success message to the client.
//        return "Embedding stored successfully!";
//    }
//
//    @GetMapping("/search")
//    public List<String> searchEmbeddings(@RequestParam String query) {
//        // 1. Generate an embedding for the search query
//        Embedding queryEmbedding = embeddingModel.embed(TextSegment.from(query)).content();
//
//        // 2. Search the embedding store for the most relevant matches
//        // We'll look for the top 5 most similar embeddings
//        List<EmbeddingMatch<TextSegment>> relevantMatches = embeddingStore.findRelevant(queryEmbedding, 5);
//
//        // 3. Extract the original text segments from the matches
//        return relevantMatches.stream()
//                .map(match -> match.embedded().text())
//                .collect(Collectors.toList());
//    }
//}
