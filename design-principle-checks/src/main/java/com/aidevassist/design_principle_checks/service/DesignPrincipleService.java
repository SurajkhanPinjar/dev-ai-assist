package com.aidevassist.design_principle_checks.service;


import com.aidevassist.model.dto.DesignPrincipleRequest;
import com.aidevassist.model.dto.DesignPrincipleResponse;

public interface DesignPrincipleService {
    DesignPrincipleResponse aiAnalyzePrinciples(DesignPrincipleRequest request);
}