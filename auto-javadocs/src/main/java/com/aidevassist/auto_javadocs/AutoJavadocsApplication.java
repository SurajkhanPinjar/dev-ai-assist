package com.aidevassist.auto_javadocs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
		"com.aidevassist.auto_javadocs",
		"com.aidev.core.ai_core"
})
public class AutoJavadocsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AutoJavadocsApplication.class, args);
	}

}
