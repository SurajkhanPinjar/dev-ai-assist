package com.aidevassist.model.dto;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class GeneratedTestFile {
    private String fileName;     // e.g., OrderServiceTest.java
    private String sourceCode;   // full test source
}
