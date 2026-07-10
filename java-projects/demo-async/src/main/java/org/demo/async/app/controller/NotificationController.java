package org.demo.async.app.controller;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import org.demo.async.app.service.EmailService;
import org.demo.async.app.service.EventService;
import org.demo.async.app.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(path = { "/notification" })
public class NotificationController {

	private final EmailService emailService;
	private final EventService eventService;
	private final NotificationService notificationService;

	@Autowired
	public NotificationController(EmailService emailService, EventService eventService, NotificationService notificationService) {
		this.emailService = emailService;
		this.eventService = eventService;
		this.notificationService = notificationService;
	}

	/**
	 * This method sends an email to the recipient asynchronously.
	 *
	 * @return Map of status message and status.
	 */
	@GetMapping(path = "/sendEmail")
	public Map<String, Object> sendEmail() {
		log.info("send email controller execution started.");
		emailService.sendEmail("demo.user@demo.org", "Welcome email", "Hi There.");
		log.info("send email controller execution finished.");
		return Collections.singletonMap("message", Boolean.TRUE);
	}

	/**
	 * This method sends HTML email to the recipient asynchronously.
	 *
	 * @return Map of status message and status.
	 */
	@GetMapping(path = "/sendHtmlEmail")
	public Map<String, Object> sendHtmlEmail() {
		log.info("send html email controller execution started.");
		String status = "success";

		CompletableFuture<Boolean> firstEmail = emailService.sendHtmlEmail("demo.user1@demo.org", "Welcome email", "Hi There User1.");
		CompletableFuture<Boolean> secondEmail = emailService.sendHtmlEmail("demo.user2@demo.org", "Welcome email", "Hi There User2.");
		CompletableFuture<Boolean> thirdEmail = emailService.sendHtmlEmail("demo.user3@demo.org", "Welcome email", "Hi There User3.");
		CompletableFuture<Boolean> fourthEmail = emailService.sendHtmlEmail("demo.user4@demo.org", "Welcome email", "Hi There User4.");
		CompletableFuture.allOf(firstEmail, secondEmail, thirdEmail, fourthEmail).join();

		log.info("send html email controller execution finished.");
		return Collections.singletonMap("message", status);
	}

	/**
	 * This method processes events asynchronously.
	 *
	 * @return Map of status message and status.
	 */
	@GetMapping(path = "/process")
	public Map<String, Object> processEventNotification() {
		log.info("process event notification controller execution started.");
		String status = "success";

		eventService.processEvent("event-1");
		eventService.processEvent("event-2");
		eventService.processEvent("event-3");
		eventService.processEvent("event-4");

		log.info("process event notification controller execution finished.");
		return Collections.singletonMap("message", status);
	}

	/**
	 * This method sends a notification to the recipient asynchronously.
	 *
	 * @return Map of status message and status.
	 */
	@GetMapping(path = "/sendNotification")
	public Map<String, Object> sendNotification() {
		log.info("send notification controller execution started.");
		String status = "success";
		notificationService.sendNotification("demo.user@demo.org", "Welcome notification");

		log.info("send notification controller execution finished.");
		return Collections.singletonMap("message", status);
	}

}