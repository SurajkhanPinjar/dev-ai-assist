package com.aidev.core.ai_core.service;

import com.aidevassist.model.dto.AnalysisRequest;
import com.aidevassist.model.dto.AnalysisResponse;

public interface AiAnalyzerService {
    AnalysisResponse analyzeWithAi(AnalysisRequest request);
}