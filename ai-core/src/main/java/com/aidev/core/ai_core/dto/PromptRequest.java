package com.aidev.core.ai_core.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class PromptRequest {

    @Schema(description = "Prompt text to send to AI", example = "Explain Spring Boot in 3 bullet points")
    private String prompt;
}