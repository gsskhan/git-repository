package org.demo.async.app.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicReference;
import org.mockito.InjectMocks;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class EmailServiceTest {

    @InjectMocks
    private EmailService emailService;

    @Test
    void testSendEmail_Success() {
        // Note: This test takes ~10s due to hardcoded sleep in EmailService.
        assertDoesNotThrow(() -> emailService.sendEmail("demo@example.com", "Subject", "Body"));
    }

    @Test
    void testSendEmail_Interrupted() throws InterruptedException {
        Thread thread = new Thread(() -> emailService.sendEmail("demo@example.com", "Subject", "Body"));
        thread.start();
        // Interrupt the thread to exercise the catch (InterruptedException) block.
        thread.interrupt();
        thread.join();
    }

    @Test
    void testSendHtmlEmail_Success() throws ExecutionException, InterruptedException {
        // Note: This test takes ~10s due to hardcoded sleep in EmailService.
        CompletableFuture<Boolean> result = emailService.sendHtmlEmail("demo@example.com", "Html Subject", "Html Body");
        assertNotNull(result);
        assertTrue(result.get());
    }

    @Test
    void testSendHtmlEmail_Interrupted() throws InterruptedException, ExecutionException {
        AtomicReference<CompletableFuture<Boolean>> resultRef = new AtomicReference<>();
        Thread thread = new Thread(() -> {
            resultRef.set(emailService.sendHtmlEmail("demo@example.com", "Html Subject", "Html Body"));
        });
        thread.start();
        // Interrupt the thread to exercise the catch (InterruptedException) block.
        thread.interrupt();
        thread.join();

        assertNotNull(resultRef.get());
        assertFalse(resultRef.get().get());
    }

}
