package org.demo.async.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * Main application class for the DemoAsyncApplication.
 * This class enables asynchronous method execution using {@link EnableAsync}
 * and is the entry point for the Spring Boot application.
 *
 */
@EnableAsync
@SpringBootApplication
public class DemoAsyncApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoAsyncApplication.class, args);
	}

}
