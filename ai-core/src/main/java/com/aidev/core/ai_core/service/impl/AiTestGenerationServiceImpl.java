//// package: com.aidev.core.ai_core.service.impl
//
//package com.aidev.core.ai_core.service.impl;
//
//import com.aidev.core.ai_core.service.AiTestGenerationService;
//import com.aidevassist.model.dto.GeneratedTestFile;
//import com.aidevassist.model.dto.TestGenRequest;
//import com.aidevassist.model.dto.TestGenResponse;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import lombok.RequiredArgsConstructor;
//import org.springframework.ai.ollama.OllamaChatModel;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//public class AiTestGenerationServiceImpl implements AiTestGenerationService {
//
//    private final OllamaChatModel chatModel;
//    private final ObjectMapper objectMapper = new ObjectMapper();
//
//    @Override
//    public TestGenResponse generateTests(TestGenRequest request) {
//        String prompt = buildPrompt(request);
//        String ai = chatModel.call(prompt);
//
//        // Expected JSON shape:
//        // {
//        //   "files":[{"fileName":"XTest.java","sourceCode":"..."}],
//        //   "summary":"...", "suggestions":["..."]
//        // }
//        try {
//            return objectMapper.readValue(ai, TestGenResponse.class);
//        } catch (Exception ex) {
//            // Fallback: wrap raw response into a single file
//            List<GeneratedTestFile> files = new ArrayList<>();
//            files.add(GeneratedTestFile.builder()
//                    .fileName((request.getClassName() == null ? "GeneratedTest" : request.getClassName()+"Test") + ".java")
//                    .sourceCode(ai)
//                    .build());
//            return TestGenResponse.builder()
//                    .files(files)
//                    .summary("AI returned non-JSON; wrapped raw output. Consider improving prompt or enabling JSON mode.")
//                    .suggestions(List.of("Ensure model prompt enforces strict JSON.", "Validate className and methodNames."))
//                    .build();
//        }
//    }
//
//    private String buildPrompt(TestGenRequest r) {
//        return """
//        You are a senior Java engineer and test architect.
//        Generate high-quality JUnit tests for the provided code.
//
//        Constraints:
//        - Use %s as the test framework (default JUnit5).
//        - Use %s for mocking (Mockito when applicable).
//        - Style: %s.
//        - Include edge cases: %s.
//        - Include parameterized tests when valuable: %s.
//        - Cover constructor, public methods, and critical branches.
//        - Use clear Arrange-Act-Assert (AAA) or Given-When-Then as requested.
//        - Avoid external I/O and network calls; mock collaborators.
//        - If needed, create minimal fakes or test data builders inside the test class.
//
//        Output: Respond ONLY in valid JSON with this schema:
//        {
//          "files": [
//            { "fileName": "OrderServiceTest.java", "sourceCode": "<full compilable test>" }
//          ],
//          "summary": "1-2 lines about coverage and focus areas",
//          "suggestions": ["Short actionable bullets to improve tests further"]
//        }
//
//        Hints:
//        - If methodNames are provided, focus on them first.
//        - Use @ExtendWith(MockitoExtension.class) and @Mock/@InjectMocks for services.
//        - Prefer ParameterizedTest for obvious input permutations.
//        - Name tests clearly (method_underTest_condition_expectedBehavior).
//
//        ClassName: %s
//        Methods: %s
//
//        Code:
//        %s
//        """.formatted(
//                nullTo(r.getFramework(), "JUNIT5"),
//                nullTo(r.getMocking(), "MOCKITO"),
//                nullTo(r.getStyle(), "AAA"),
//                r.isIncludeEdgeCases(),
//                r.isIncludeParameterized(),
//                nullTo(r.getClassName(), "<unknown>"),
//                r.getMethodNames() == null ? "[]" : r.getMethodNames().toString(),
//                r.getCode()
//        );
//    }
//
//    private static String nullTo(String val, String def) {
//        return (val == null || val.isBlank()) ? def : val;
//    }
//}