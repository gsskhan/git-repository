package org.demo.microservice.springconfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class DemoSpringConfigApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoSpringConfigApplication.class, args);
	}

}
