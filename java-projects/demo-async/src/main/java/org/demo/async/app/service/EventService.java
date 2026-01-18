package org.demo.async.app.service;

import lombok.extern.slf4j.Slf4j;
import org.demo.async.app.config.AsyncConfig;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
public class EventService {

    /**
     * This method processes an event asynchronously.
     *
     * @param event
     *            the event to be processed.
     */
    @Async(value = AsyncConfig.VIRTUAL_THREAD_TASK_EXECUTOR)
    public void processEvent(String event) {
        try {
            log.info("processEvent method start by {} at {}.", Thread.currentThread().toString(), LocalDateTime.now());
            Thread.sleep(5000);
            log.info("Event processed: [{}].", event);
            log.info("processEvent method ended by {} at {}.", Thread.currentThread().toString(), LocalDateTime.now());
        } catch (Exception e) {
            log.error("processEvent method failed.", e);
        }
    }
}
