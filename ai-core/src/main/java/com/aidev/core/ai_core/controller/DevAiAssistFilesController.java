package com.aidev.core.ai_core.controller;

import com.aidev.core.ai_core.service.impl.WeaviateFileServiceImpl;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/weaviate")
@RequiredArgsConstructor
public class DevAiAssistFilesController {

    private final WeaviateFileServiceImpl weaviateFileService;

    @PostMapping("/add-file/{type}")
    public Map<String, String> addFile(@RequestParam String fileName,
                                       @Parameter(
                                               description = "Select the type of AI service",
                                               schema = @Schema(allowableValues = {
                                                       "java-docs",
                                                       "code-optimizer",
                                                       "code-reviewer",
                                                       "junit-test"
                                               })
                                       )
                                       @RequestParam String type) {
        return weaviateFileService.addFileWeaviate(fileName, type);
    }

    // ---------- Query Documents ----------
    @PostMapping("/query")
    public Map<String, String> query(
            @Parameter(
                    description = "Select the type of AI service",
                    schema = @Schema(allowableValues = {
                            "java-docs",
                            "code-optimizer",
                            "code-reviewer",
                            "junit-test"
                    })
            )
            @RequestParam String type,
            @RequestBody Map<String, String> request
    ) {
        return weaviateFileService.query(type, request);
    }

    // ---------- Clear All Data ----------
    @DeleteMapping("/clear")
    public Map<String, String> clearStore(@Parameter(
            description = "Select the type of AI service",
            schema = @Schema(allowableValues = {
                    "java-docs",
                    "code-optimizer",
                    "code-reviewer",
                    "junit-test"
            })
    )
                                          @RequestParam String type) {
        return weaviateFileService.clearStore(type);
    }

}
