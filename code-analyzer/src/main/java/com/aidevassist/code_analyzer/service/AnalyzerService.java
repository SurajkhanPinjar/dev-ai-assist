package com.aidevassist.code_analyzer.service;

import com.aidevassist.model.dto.AnalysisRequest;
import com.aidevassist.model.dto.AnalysisResponse;

public interface AnalyzerService {
    AnalysisResponse analyze(AnalysisRequest request);     // Rule-based
    AnalysisResponse aiAnalyze(AnalysisRequest request);   // AI-based
}