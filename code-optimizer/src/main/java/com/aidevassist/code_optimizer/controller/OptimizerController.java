package com.aidevassist.code_optimizer.controller;

import com.aidevassist.code_optimizer.service.OptimizerService;
import com.aidevassist.model.dto.OptimizationRequest;
import com.aidevassist.model.dto.OptimizationResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/optimizer")
@RequiredArgsConstructor
@Tag(name = "Code Optimizer", description = "Suggests improvements and refactorings for Java code")
public class OptimizerController {

    private final OptimizerService optimizerService;

    @PostMapping("/optimize")
    @Operation(
            summary = "Optimize Java Code",
            description = "Takes Java source code and returns optimized code with suggestions"
    )
    public OptimizationResponse optimizeCode(@RequestBody OptimizationRequest request) {
        return optimizerService.optimizeCode(request);
    }
}