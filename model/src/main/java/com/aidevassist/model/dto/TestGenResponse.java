package com.aidevassist.model.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TestGenResponse {
    private List<GeneratedTestFile> files;
    private String summary;              // overall notes
    private List<String> suggestions;    // follow-ups (mocks, data builders, etc.)
}