package com.aidev.core.ai_core.service;

import com.aidevassist.model.dto.JavaDocRequest;
import com.aidevassist.model.dto.JavaDocResponse;

public interface JavadocService {
    JavaDocResponse generateJavadoc(JavaDocRequest request);
}
