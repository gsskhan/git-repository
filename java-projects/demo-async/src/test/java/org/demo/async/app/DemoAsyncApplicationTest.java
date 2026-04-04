package org.demo.async.app;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedStatic;
import org.springframework.boot.SpringApplication;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mockStatic;

@ExtendWith(SpringExtension.class)
class DemoAsyncApplicationTest {

	@Test
	void main() {
		try (MockedStatic<SpringApplication> mockedSpringApplication = mockStatic(SpringApplication.class)) {
			String[] args = new String[] {};
			DemoAsyncApplication.main(args);
			mockedSpringApplication.verify(() -> SpringApplication.run(DemoAsyncApplication.class, args));
		}
	}

	@Test
	void testInstantiation() {
		assertNotNull(new DemoAsyncApplication());
	}
}
