//package com.aidevassist.bugfinder.controller;
//
//import com.aidevassist.bugfinder.service.BugFinderAnalyzer;
//import com.aidevassist.model.dto.AnalysisRequest;
//import com.aidevassist.model.dto.AnalysisResponse;
//import com.aidevassist.model.enums.AnalysisType;
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.tags.Tag;
//import lombok.RequiredArgsConstructor;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/api/bug-finder")
//@RequiredArgsConstructor
//@Tag(name = "Bug Finder", description = "Detects bugs and bad practices in Java code")
//public class BugFinderController {
//
//    private final BugFinderAnalyzer bugFinderAnalyzer;
//
//    @PostMapping("/analyzer")
//    @Operation(summary = "Analyze Java Code", description = "Detects common issues in Java source code")
//    public AnalysisResponse analyze(@RequestBody AnalysisRequest analysisRequest){
//        analysisRequest.setType(AnalysisType.BUG_FINDING);
//        return bugFinderAnalyzer.analyze(analysisRequest);
//    }
//}
