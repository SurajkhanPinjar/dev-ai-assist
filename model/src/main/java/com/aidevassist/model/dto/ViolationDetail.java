package com.aidevassist.model.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ViolationDetail {
    private String principle;
    private String description;
    private String lineSnippet;
}