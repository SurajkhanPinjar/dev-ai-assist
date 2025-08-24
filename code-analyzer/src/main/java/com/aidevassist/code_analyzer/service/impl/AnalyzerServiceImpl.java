package com.aidevassist.code_analyzer.service.impl;

import com.aidevassist.code_analyzer.service.AnalyzerService;
import com.aidevassist.model.dto.AnalysisRequest;
import com.aidevassist.model.dto.AnalysisResponse;
import com.aidevassist.model.dto.ViolationDetail;
import com.aidev.core.ai_core.service.AiAnalyzerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AnalyzerServiceImpl implements AnalyzerService {

    private final AiAnalyzerService aiAnalyzerService;

    @Override
    public AnalysisResponse analyze(AnalysisRequest request) {
        String code = request.getCode();
        List<ViolationDetail> violations = new ArrayList<>();

        if (code.contains("System.out.println")) {
            violations.add(ViolationDetail.builder()
                    .message("Avoid System.out.println; use a logger instead.")
                    .build());
        }

        if (code.contains("import java.util.*;")) {
            violations.add(ViolationDetail.builder()
                    .message("Avoid wildcard imports; use specific imports.")
                    .build());
        }

        return AnalysisResponse.builder()
                .violations(violations)
                .build();
    }

    @Override
    public AnalysisResponse aiAnalyze(AnalysisRequest request) {
        return aiAnalyzerService.analyzeWithAi(request);
    }
}