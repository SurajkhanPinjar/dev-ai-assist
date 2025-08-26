package com.aidevassist.auto_javadocs.controller;

import com.aidev.core.ai_core.service.JavadocService;
import com.aidevassist.model.dto.JavaDocRequest;
import com.aidevassist.model.dto.JavaDocResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/javadoc")
@RequiredArgsConstructor
public class JavadocController {

    private final JavadocService javadocService;

    @PostMapping("/generate")
    public ResponseEntity<JavaDocResponse> generate(@RequestBody JavaDocRequest request) {
        return ResponseEntity.ok(javadocService.generateJavadoc(request));
    }
}