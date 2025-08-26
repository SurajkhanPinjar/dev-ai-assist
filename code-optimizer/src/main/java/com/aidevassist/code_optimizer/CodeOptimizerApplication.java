package com.aidevassist.code_optimizer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication(scanBasePackages = {
		"com.aidevassist",
		"com.aidev.core.ai_core"
})
@EnableDiscoveryClient
public class CodeOptimizerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CodeOptimizerApplication.class, args);
	}

}
