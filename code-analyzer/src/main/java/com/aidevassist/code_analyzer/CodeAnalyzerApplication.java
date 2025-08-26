package com.aidevassist.code_analyzer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = {
		"com.aidevassist",
		"com.aidev.core.ai_core"
})
public class CodeAnalyzerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CodeAnalyzerApplication.class, args);
	}

}
