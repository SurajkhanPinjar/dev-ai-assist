package com.aidevassist.model.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DesignPrincipleResponse {
    private List<DesignPrincipleViolation> violations;
    private String summary;                    // overall summary
    private List<String> suggestions;          // actionable fixes
}