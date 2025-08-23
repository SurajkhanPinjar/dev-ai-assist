package com.aidevassist.code_optimizer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor   // 👈 Needed for Jackson
@AllArgsConstructor
public class CodeRequest {
    private String code;
}
