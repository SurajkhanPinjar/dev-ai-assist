package com.aidevassist.api_gateway.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class GatewayController {

    @PostMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("API Gateway Working!");
    }
}