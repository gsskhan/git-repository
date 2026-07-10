package org.demo.async.app.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import org.demo.async.app.service.EmailService;
import org.demo.async.app.service.EventService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class NotificationControllerTest {

    @Mock
    private EmailService emailService;

    @Mock
    private EventService eventService;

    @InjectMocks
    private NotificationController notificationController;

    @Test
    void testSendEmail() {
        Map<String, Object> result = notificationController.sendEmail();

        assertNotNull(result);
        assertEquals(Boolean.TRUE, result.get("message"));
        verify(emailService).sendEmail("demo.user@demo.org", "Welcome email", "Hi There.");
    }

    @Test
    void testSendHtmlEmail() {
        when(emailService.sendHtmlEmail(anyString(), anyString(), anyString()))
                .thenReturn(CompletableFuture.completedFuture(Boolean.TRUE));

        Map<String, Object> result = notificationController.sendHtmlEmail();

        assertNotNull(result);
        assertEquals("success", result.get("message"));

        verify(emailService).sendHtmlEmail("demo.user1@demo.org", "Welcome email", "Hi There User1.");
        verify(emailService).sendHtmlEmail("demo.user2@demo.org", "Welcome email", "Hi There User2.");
        verify(emailService).sendHtmlEmail("demo.user3@demo.org", "Welcome email", "Hi There User3.");
        verify(emailService).sendHtmlEmail("demo.user4@demo.org", "Welcome email", "Hi There User4.");
    }

    @Test
    void testProcessEventNotification() {
        Map<String, Object> result = notificationController.processEventNotification();

        assertNotNull(result);
        assertEquals("success", result.get("message"));

        verify(eventService).processEvent("event-1");
        verify(eventService).processEvent("event-2");
        verify(eventService).processEvent("event-3");
        verify(eventService).processEvent("event-4");
    }
}
