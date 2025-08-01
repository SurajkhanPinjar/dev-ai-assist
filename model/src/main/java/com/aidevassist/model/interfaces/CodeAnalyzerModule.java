package com.aidevassist.model.interfaces;

import com.aidevassist.model.dto.AnalysisRequest;
import com.aidevassist.model.dto.AnalysisResponse;

public interface CodeAnalyzerModule {
    AnalysisResponse analyze(AnalysisRequest analysisRequest);
}
