package com.aidevassist.model.dto;

import com.aidevassist.model.enums.AnalysisType;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnalysisRequest {
    @NotBlank
    private String code;

    private AnalysisType type; // BUG_FINDING, OPTIMIZATION, JAVADOC, DESIGN_PRINCIPLE
}