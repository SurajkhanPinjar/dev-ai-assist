package com.aidevassist.design_principle_checks.controller;

import com.aidevassist.design_principle_checks.service.DesignPrincipleService;
import com.aidevassist.model.dto.DesignPrincipleRequest;
import com.aidevassist.model.dto.DesignPrincipleResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/design-principles")
@RequiredArgsConstructor
@Tag(name = "Design Principles Analyzer", description = "Analyze Java code against SOLID design principles")
public class DesignPrincipleController {

    private final DesignPrincipleService designPrincipleService;

    @PostMapping("/analyze")
    @Operation(
            summary = "Analyze Java code for design principle violations",
            description = "Checks the provided Java code against SOLID principles (SRP, OCP, LSP, ISP, DIP)"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Analysis completed successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = DesignPrincipleResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    public ResponseEntity<DesignPrincipleResponse> analyze(
            @Valid @RequestBody DesignPrincipleRequest request) {
        return ResponseEntity.ok(designPrincipleService.aiAnalyzePrinciples(request));
    }
}