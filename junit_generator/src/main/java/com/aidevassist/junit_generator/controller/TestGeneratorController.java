// package: com.aidevassist.junit_generator.controller

package com.aidevassist.junit_generator.controller;

import com.aidevassist.junit_generator.service.TestGeneratorService;
import com.aidevassist.model.dto.TestGenRequest;
import com.aidevassist.model.dto.TestGenResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/test-gen")
@RequiredArgsConstructor
@Tag(name = "JUnit Test Generator", description = "Generate JUnit tests from Java code using AI")
public class TestGeneratorController {

    private final TestGeneratorService testGeneratorService;

    @PostMapping("/generate")
    @Operation(
            summary = "Generate unit tests",
            description = "Generates JUnit (with Mockito) tests for the provided Java class and methods."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Generated tests",
            content = @Content(schema = @Schema(implementation = TestGenResponse.class))
    )
    public ResponseEntity<TestGenResponse> generate(@Valid @RequestBody TestGenRequest request) {
        return ResponseEntity.ok(testGeneratorService.aiGenerateTests(request));
    }
}