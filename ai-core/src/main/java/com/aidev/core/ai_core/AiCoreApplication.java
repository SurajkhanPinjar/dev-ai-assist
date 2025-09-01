package com.aidev.core.ai_core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
//@EnableDiscoveryClient
public class AiCoreApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(AiCoreApplication.class);

		// Detect if running inside Docker
		String profile = isRunningInDocker() ? "docker" : "local";
		app.setAdditionalProfiles(profile);

		app.run(args);
	}

	private static boolean isRunningInDocker() {
		// Simple heuristic: Docker often sets this env var
		String cgroup = System.getenv("HOSTNAME");
		return cgroup != null && new File("/.dockerenv").exists();
	}

}
