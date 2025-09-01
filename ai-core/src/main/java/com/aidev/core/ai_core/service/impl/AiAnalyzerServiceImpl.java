//package com.aidev.core.ai_core.service.impl;
//
//import com.aidev.core.ai_core.service.AiAnalyzerService;
//import com.aidevassist.model.dto.AnalysisRequest;
//import com.aidevassist.model.dto.AnalysisResponse;
//import com.aidevassist.model.dto.ViolationDetail;
//import lombok.RequiredArgsConstructor;
//import org.springframework.ai.ollama.OllamaChatModel;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//public class AiAnalyzerServiceImpl implements AiAnalyzerService {
//
//    private final OllamaChatModel chatModel;
//
//    @Override
//    public AnalysisResponse analyzeWithAi(AnalysisRequest request) {
//        String prompt = """
//                You are a senior Java static code analyzer.
//                Analyze the given Java code and detect rule violations.
//                Return the result in JSON array format only:
//
//                [
//                  {"message": "<short violation message>"}
//                ]
//
//                Code:
//                """ + request.getCode();
//
//        String aiOutput = chatModel.call(prompt);
//
//        // TODO: parse `aiOutput` JSON into List<ViolationDetail>
//        // For now, return as single violation (just to make app run)
//        ViolationDetail violation = ViolationDetail.builder()
//                .message(aiOutput)
//                .build();
//
//        return AnalysisResponse.builder()
//                .violations(List.of(violation))
//                .build();
//    }
//}