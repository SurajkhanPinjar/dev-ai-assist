package com.aidevassist.model.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnalysisResponse {
    private List<ViolationDetail> violations;
//    private String summary;
//    private List<String> suggestions;
}