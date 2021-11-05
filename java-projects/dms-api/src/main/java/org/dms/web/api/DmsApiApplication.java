package org.dms.web.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = { "org.dms.web.api" })
public class DmsApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(DmsApiApplication.class, args);
	}

}
