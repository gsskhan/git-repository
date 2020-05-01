package org.demo.ws.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"org.demo.ws.server"})
@SpringBootApplication
public class WebServiceServerApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(WebServiceServerApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(WebServiceServerApplication.class, args);

	}

}
