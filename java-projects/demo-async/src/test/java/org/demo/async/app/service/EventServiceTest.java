package org.demo.async.app.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class EventServiceTest {

    @InjectMocks
    private EventService eventService;

    @Test
    void testProcessEvent_Success() {
        // Note: This test takes ~5s due to hardcoded sleep in EventService.
        assertDoesNotThrow(() -> eventService.processEvent("event-test"));
    }

    @Test
    void testProcessEvent_Interrupted() throws InterruptedException {
        Thread thread = new Thread(() -> eventService.processEvent("event-test"));
        thread.start();
        // Interrupt the thread to exercise the catch block.
        thread.interrupt();
        thread.join();
    }
}
