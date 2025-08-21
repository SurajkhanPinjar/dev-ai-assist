package com.aidevassist.model.interfaces;

import com.aidevassist.model.dto.AnalysisRequest;
import com.aidevassist.model.dto.AnalysisResponse;
import com.aidevassist.model.enums.AnalysisType;

public interface CodeAnalyzerModule {
    /**
     * Checks if this analyzer supports the given analysis type.
     * Example: BUG_FINDING, OPTIMIZATION, JAVADOC
     */
    boolean supports(AnalysisType type);

    /**
     * Perform the analysis on given request and return response.
     */
    AnalysisResponse analyze(AnalysisRequest request);
}
