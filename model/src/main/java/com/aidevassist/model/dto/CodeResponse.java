package com.aidevassist.model.dto;

import java.util.List;

public record CodeResponse(String optimizedCode, List<String> suggestions) {
}
