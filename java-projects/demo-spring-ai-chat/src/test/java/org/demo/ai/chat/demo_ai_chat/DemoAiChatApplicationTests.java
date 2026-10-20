package org.demo.ai.chat.demo_ai_chat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class DemoAiChatApplicationTests {

	@Test
	void testMainMethod(){
		// Arrange
		String[] args = {"--server.port=8081"};

		// Act & Assert
		try (MockedStatic<SpringApplication> mockedSpringApplication = Mockito.mockStatic(SpringApplication.class)) {
			// Stub using precise Matchers to avoid signature confusion
			mockedSpringApplication.when(() -> SpringApplication.run(
					ArgumentMatchers.eq(DemoAiChatApplication.class),
					ArgumentMatchers.eq(args))
			).thenReturn(Mockito.mock(ConfigurableApplicationContext.class));

			// Execute the main method
			DemoAiChatApplication.main(args);

			// Verify with clear argument anchors
			mockedSpringApplication.verify(
					() -> SpringApplication.run(
							ArgumentMatchers.eq(DemoAiChatApplication.class),
							ArgumentMatchers.eq(args)
					),
					times(1)
			);
        }
	}
}
