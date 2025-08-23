package com.aidevassist.code_optimizer.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OptimizationResponse {
    private String optimizedCode;
    private List<String> suggestions;

    public CodeResponse toCodeResponse() {
        return new CodeResponse(optimizedCode, suggestions);
    }

}
