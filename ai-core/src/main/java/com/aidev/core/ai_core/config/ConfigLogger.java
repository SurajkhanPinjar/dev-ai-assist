package com.aidev.core.ai_core.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class ConfigLogger implements CommandLineRunner {

    @Value("${spring.ai.ollama.base-url:NOT-SET}")
    private String baseUrl;

    private final Environment env;

    public ConfigLogger(Environment env) {
        this.env = env;
    }

    @Override
    public void run(String... args) {
        System.err.println("========================================");
        System.err.println("👉 Ollama Base URL (from @Value): " + baseUrl);
        System.err.println("👉 Ollama Base URL (from Environment): "
                + env.getProperty("spring.ai.ollama.base-url"));
        System.err.println("👉 Active Profiles: "
                + String.join(", ", env.getActiveProfiles()));
        System.err.println("========================================");
    }
}