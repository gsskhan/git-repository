package org.dms.middleware.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication(scanBasePackages = { "org.dms.middleware.app" })
public class DmsApp extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(DmsApp.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(DmsApp.class);
	}

}