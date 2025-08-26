package com.aidevassist.junit_generator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
		"com.aidevassist.junit_generator",
		"com.aidevassist.model",
		"com.aidev.core.ai_core"
})
public class JunitGeneratorApplication {

	public static void main(String[] args) {
		SpringApplication.run(JunitGeneratorApplication.class, args);
	}

}
