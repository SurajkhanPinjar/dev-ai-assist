package com.aidevassist.code_optimizer.controller;

import com.aidevassist.code_optimizer.dto.CodeRequest;
import com.aidevassist.code_optimizer.dto.CodeResponse;
import com.aidevassist.code_optimizer.service.OptimizerService;
import com.aidevassist.code_optimizer.service.impl.HybridOptimizerService;
import com.aidevassist.model.dto.OptimizationRequest;
import com.aidevassist.model.dto.OptimizationResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/optimizer")
@RequiredArgsConstructor
@Tag(name = "Code Optimizer", description = "Suggests improvements and refactorings for Java code")
public class OptimizerController {

    private final OptimizerService optimizerService;

    private final HybridOptimizerService hybridOptimizerService;

    @PostMapping("/optimizeCode")
    @Operation(summary = "Optimize code (using OptimizationRequest)",
            description = "Send raw source code and get optimized code with suggestions.")
    public ResponseEntity<OptimizationResponse> optimizeCode(@RequestBody OptimizationRequest request) {
        return ResponseEntity.ok(optimizerService.optimizeCode(request));
    }

    @PostMapping("/optimize")
    @Operation(summary = "Optimize code (using CodeRequest)",
            description = "Alternative endpoint that accepts CodeRequest DTO.")
    public ResponseEntity<CodeResponse> optimize(@RequestBody CodeRequest request) {
        return ResponseEntity.ok(optimizerService.optimize(request));
    }

    @PostMapping("/optimize/hybrid")
    @Operation(summary = "Hybrid optimization",
            description = "Runs rule-based optimization first, then refines with AI.")
    public ResponseEntity<OptimizationResponse> optimizeHybrid(@RequestBody OptimizationRequest request) {
        return ResponseEntity.ok(hybridOptimizerService.optimize(request));
    }
}