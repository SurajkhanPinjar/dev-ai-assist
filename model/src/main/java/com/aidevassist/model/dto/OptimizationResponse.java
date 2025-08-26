package com.aidevassist.model.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OptimizationResponse {
    private String optimizedCode;       // final code after optimization
    private List<String> suggestions;   // list of human-readable suggestions
}