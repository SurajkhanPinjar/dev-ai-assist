package com.aidevassist.code_optimizer.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/optimizer")
public class OptimizerPingController {

    @GetMapping("/ping")
    public String ping() {
        return "Code Optimizer is running!";
    }
}