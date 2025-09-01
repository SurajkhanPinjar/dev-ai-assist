//package com.aidev.core.ai_core.service.impl;
//
//import com.aidev.core.ai_core.service.JavadocService;
//
//import com.aidevassist.model.dto.JavaDocRequest;
//import com.aidevassist.model.dto.JavaDocResponse;
//import lombok.RequiredArgsConstructor;
//import org.springframework.ai.ollama.OllamaChatModel;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//public class JavadocServiceImpl implements JavadocService {
//
//    private final OllamaChatModel chatModel;
//
//    @Override
//    public JavaDocResponse generateJavadoc(JavaDocRequest request) {
//        String prompt = """
//            You are a senior Java developer.
//            Add proper Javadoc comments to the following code.
//            Return only the full updated code without explanations.
//
//            Code:
//            """ + request.getClassCode();
//
//        String response = chatModel.call(prompt);
//        return new JavaDocResponse(response);
//    }
//}