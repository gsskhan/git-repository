package org.demo.async.app.config;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.concurrent.Executor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class AsyncConfigTest {

    @InjectMocks
    private AsyncConfig asyncConfig;

    @Test
    void testVirtualThreadTaskExecutor() {
        Executor executor = asyncConfig.virtualThreadTaskExecutor();
        assertNotNull(executor, "Virtual thread task executor should not be null");
        assertDoesNotThrow(() -> executor.execute(() -> {}));
    }

    @Test
    void testTaskExecutor() {
        Executor executor = asyncConfig.taskExecutor();
        assertNotNull(executor, "Task executor should not be null");
        assertDoesNotThrow(() -> executor.execute(() -> {}));
    }
}
