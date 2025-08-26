//package com.aidevassist.bugfinder.service;
//
//import com.aidevassist.model.dto.AnalysisRequest;
//import com.aidevassist.model.dto.AnalysisResponse;
//import com.aidevassist.model.dto.ViolationDetail;
//import com.aidevassist.model.enums.AnalysisType;
//import com.aidevassist.model.interfaces.CodeAnalyzerModule;
//import com.github.javaparser.JavaParser;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//public class BugFinderAnalyzer implements CodeAnalyzerModule {
//
//    private final JavaParser parser = new JavaParser();
//
//
//    @Override
//    public boolean supports(AnalysisType type) {
//        return type == AnalysisType.BUG_FINDING;
//    }
//
//    @Override
//    public AnalysisResponse analyze(AnalysisRequest analysisRequest) {
//        List<ViolationDetail> violations = new ArrayList<>();
//
//        parser.parse(analysisRequest.getCode()).ifSuccessful(cu -> {
//            // Example check: unused imports:
//            cu.getImports().forEach(importDecl -> {
//                String importName = importDecl.getNameAsString();
//                if(!cu.toString().contains(importName.substring(importName.lastIndexOf('.') + 1))){
//                    violations.add(new ViolationDetail("Unused import: "+ importName));
//                }
//            });
//
//            // Example check: System.out.println usage (bad practice)
//            cu.findAll(com.github.javaparser.ast.expr.MethodCallExpr.class).forEach(method ->{
//                if(method.toString().contains("System.out.println")){
//                    violations.add(new ViolationDetail("Avoid using System.out.println, use logger instead."));
//                }
//            });
//
//        });
//
//        return new AnalysisResponse(violations);
//    }
//}
