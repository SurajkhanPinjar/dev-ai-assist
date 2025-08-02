package com.aidevassist.code_analyzer.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/analyzer")
public class AnalyzerPingController {

    @GetMapping("/ping")
    public String ping() {
        return "Code analyzer is running!";
    }
}