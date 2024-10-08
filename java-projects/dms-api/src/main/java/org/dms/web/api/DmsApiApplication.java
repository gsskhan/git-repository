package org.dms.web.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = { "org.dms.web.api" })
public class DmsApiApplication {

	/**
	 * Starts the Spring Boot application.
	 * 
	 * @param args The command line arguments for the application.
	 */
	public static void main(String[] args) {
		SpringApplication.run(DmsApiApplication.class, args);
	}

}
