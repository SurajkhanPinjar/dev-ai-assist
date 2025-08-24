package com.aidevassist.design_principle_checks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
		"com.aidevassist.design_principle_checks",
		"com.aidevassist.model",
		"com.aidev.core.ai_core"
})
public class DesignPrincipleChecksApplication {

	public static void main(String[] args) {
		SpringApplication.run(DesignPrincipleChecksApplication.class, args);
	}

}
