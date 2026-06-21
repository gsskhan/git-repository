package org.demo.ai;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class DemoSpringAiApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoSpringAiApplication.class, args);
	}

	@Bean
	public CommandLineRunner runner(ChatClient.Builder builder) {
		return args -> {
			ChatClient chatClient = builder.build();
			String response = chatClient.prompt()
										.system("You are a helpful assistant. Respond in concise manner.")
										.user("Tell me a joke")
										.call().content();

			log.info("Response: {}", response);
		};
	}

}
