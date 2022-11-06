package org.dms.web.api.config;

import org.springframework.context.annotation.*;

import io.swagger.v3.oas.models.*;
import io.swagger.v3.oas.models.info.*;

/**
 * Swagger UI can be found at - http://localhost:8080/swagger-ui/index.html
 * 
 * @author gsskhan
 *
 */

@Configuration
public class ApiDocumentationConfig {

	@Bean
	public OpenAPI springShopOpenAPI() {
		return new OpenAPI()
				.info(new Info()
						.title("Document Management System API")
						.description("Document Management System Application")
						.version("v0.0.1")
						.license(new License()
								.name("Apache 2.0")
								.url("http://springdoc.org")))
				.externalDocs(new ExternalDocumentation()
						.description("Document Management System Wiki/Confluence Documentation")
						.url("https://github.com/gsskhan/git-repository/tree/master/java-projects/dms-api"));
	}

}
