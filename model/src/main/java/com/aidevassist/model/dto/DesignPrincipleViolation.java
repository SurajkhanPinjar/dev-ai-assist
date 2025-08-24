package com.aidevassist.model.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DesignPrincipleViolation {
    private String principle;    // e.g. "SRP"
    private String message;      // e.g. "Class violates SRP by handling multiple responsibilities"
    private String lineSnippet;  // (optional) offending code snippet
}