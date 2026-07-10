package org.demo.async.app.service;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;

import org.demo.async.app.config.AsyncConfig;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class NotificationService {

	/**
	 * This method sends a notification asynchronously.
	 *
	 * @param recipient
	 *            the recipient of the notification.
	 * @param message
	 *            the message content.
	 */
	@Async(value = AsyncConfig.VIRTUAL_THREAD_TASK_EXECUTOR)
	public void sendNotification(String recipient, String message) {
		try {
			log.info("sendNotification method start by {} at {}.", Thread.currentThread(), LocalDateTime.now());
			Thread.sleep(2000);
			log.info("Notification sent to: [{}], Message: [{}].", recipient, message);
			log.info("sendNotification method ended by {} at {}.", Thread.currentThread(), LocalDateTime.now());
		} catch (InterruptedException e) {
			log.error("sendNotification method interrupted.", e);
			Thread.currentThread().interrupt();
		} catch (Exception e) {
			log.error("sendNotification method failed.", e);
		}
	}

	/**
	 * This method sends a notification with status asynchronously.
	 *
	 * @param recipient
	 *            the recipient of the notification.
	 * @param message
	 *            the message content.
	 * @return a CompletableFuture that completes with a boolean indicating success.
	 */
	@Async(value = AsyncConfig.VIRTUAL_THREAD_TASK_EXECUTOR)
	public CompletableFuture<Boolean> sendNotificationWithStatus(String recipient, String message) {
		try {
			log.info("sendNotificationWithStatus method start by {} at {}.", Thread.currentThread(), LocalDateTime.now());
			Thread.sleep(2000);
			log.info("Notification sent with status to: [{}], Message: [{}].", recipient, message);
			log.info("sendNotificationWithStatus method ended by {} at {}.", Thread.currentThread(), LocalDateTime.now());
		} catch (InterruptedException e) {
			log.error("sendNotificationWithStatus method interrupted.", e);
			Thread.currentThread().interrupt();
			return CompletableFuture.completedFuture(Boolean.FALSE);
		} catch (Exception e) {
			log.error("sendNotificationWithStatus method failed.", e);
			return CompletableFuture.completedFuture(Boolean.FALSE);
		}
		return CompletableFuture.completedFuture(Boolean.TRUE);
	}
}
