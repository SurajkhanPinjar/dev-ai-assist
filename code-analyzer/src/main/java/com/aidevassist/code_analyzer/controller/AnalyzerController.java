package com.aidevassist.code_analyzer.controller;

import com.aidevassist.code_analyzer.service.AnalyzerService;
import com.aidevassist.model.dto.AnalysisRequest;
import com.aidevassist.model.dto.AnalysisResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/analyzer")
@RequiredArgsConstructor
@Tag(name = "Code Analyzer", description = "Detects issues and anti-patterns in Java code")
public class AnalyzerController {

    private final AnalyzerService analyzerService;

    @PostMapping("/analyze")
    @Operation(summary = "Rule-based analyzer", description = "Detects issues using static rules")
    public ResponseEntity<AnalysisResponse> analyze(@RequestBody AnalysisRequest request) {
        return ResponseEntity.ok(analyzerService.analyze(request));
    }

    @PostMapping("/ai-analyze")
    @Operation(summary = "AI analyzer", description = "Detects issues using AI")
    public ResponseEntity<AnalysisResponse> aiAnalyze(@RequestBody AnalysisRequest request) {
        return ResponseEntity.ok(analyzerService.aiAnalyze(request));
    }
}