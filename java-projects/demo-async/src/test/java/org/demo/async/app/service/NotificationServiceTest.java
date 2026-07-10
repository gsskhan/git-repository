package org.demo.async.app.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicReference;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class NotificationServiceTest {

    @InjectMocks
    private NotificationService notificationService;

    @Test
    void testSendNotification_Success() {
        assertDoesNotThrow(() -> notificationService.sendNotification("demo@example.com", "Hello Message"));
    }

    @Test
    void testSendNotification_Interrupted() throws InterruptedException {
        Thread thread = new Thread(() -> notificationService.sendNotification("demo@example.com", "Hello Message"));
        thread.start();
        thread.interrupt();
        thread.join();
    }

    @Test
    void testSendNotificationWithStatus_Success() throws ExecutionException, InterruptedException {
        CompletableFuture<Boolean> result = notificationService.sendNotificationWithStatus("demo@example.com", "Hello Message");
        assertNotNull(result);
        assertTrue(result.get());
    }

    @Test
    void testSendNotificationWithStatus_Interrupted() throws InterruptedException, ExecutionException {
        AtomicReference<CompletableFuture<Boolean>> resultRef = new AtomicReference<>();
        Thread thread = new Thread(() -> {
            resultRef.set(notificationService.sendNotificationWithStatus("demo@example.com", "Hello Message"));
        });
        thread.start();
        thread.interrupt();
        thread.join();

        assertNotNull(resultRef.get());
        assertFalse(resultRef.get().get());
    }

}
