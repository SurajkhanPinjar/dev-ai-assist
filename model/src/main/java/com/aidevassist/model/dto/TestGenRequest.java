package com.aidevassist.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class TestGenRequest {
    @NotBlank
    private String code;                 // Source code to test
    private String className;            // Optional: target class name
    private List<String> methodNames;    // Optional: focus methods
    @Builder.Default private String framework = "JUNIT5"; // JUNIT5
    @Builder.Default private String mocking = "MOCKITO";  // MOCKITO / NONE
    @Builder.Default private String style = "AAA";        // AAA / GIVEN_WHEN_THEN
    @Builder.Default private boolean includeEdgeCases = true;
    @Builder.Default private boolean includeParameterized = true;
}