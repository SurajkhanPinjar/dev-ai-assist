//package com.aidevassist.code_optimizer.service.impl;
//
//import com.aidevassist.code_optimizer.service.OptimizerService;
//import com.aidevassist.model.dto.CodeRequest;
//import com.aidevassist.model.dto.CodeResponse;
//import com.aidevassist.model.dto.OptimizationRequest;
//import com.aidevassist.model.dto.OptimizationResponse;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//public class OptimizerServiceImplOld implements OptimizerService {
//
//
//    @Override
//    public OptimizationResponse optimizeCode(OptimizationRequest request) {
//        String code = request.getSourceCode();
//        List<String> suggestions = new ArrayList<>();
//
//        // Example optimization: remove unused imports
//        if (code.contains("import java.util.*;")) {
//            suggestions.add("Avoid wildcard imports. Replace with specific imports.");
//            code = code.replace("import java.util.*;", "import java.util.List;\nimport java.util.Map;");
//        }
//
//        // Example optimization: replace System.out.println
//        if (code.contains("System.out.println")) {
//            suggestions.add("Use a Logger instead of System.out.println");
//            code = code.replace("System.out.println", "logger.info");
//        }
//
//        return new OptimizationResponse(code, suggestions);
//    }
//
//    @Override
//    public CodeResponse optimize(CodeRequest request) {
//        return null;
//    }
//}