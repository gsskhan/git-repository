package org.demo.async.app.controller;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import org.demo.async.app.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(path = { "/notification" })
public class NotificationController {

	@Autowired
	EmailService emailService;

	@GetMapping(path = "/send")
	public Map<String, Object> sendNotification() {
		log.info("send notification controller execution started.");		
		Boolean status = Boolean.TRUE;
		emailService.sendEmail("demo.user@demo.org", "Welcome email", "Hi There.");		
		log.info("send notification controller execution finished.");
		return Collections.singletonMap("message", status);
	}
	
	@GetMapping(path = "/sendHtml")
	public Map<String, Object> sendHtmlNotification() {
		log.info("send html notification controller execution started.");
		String status = "success";
		
		CompletableFuture<Boolean> FirstEmail = emailService.sendHtmlEmail("demo.user1@demo.org", "Welcome email", "Hi There User1.");
		CompletableFuture<Boolean> secondEmail = emailService.sendHtmlEmail("demo.user2@demo.org", "Welcome email", "Hi There User2.");
		CompletableFuture<Boolean> thirdEmail = emailService.sendHtmlEmail("demo.user3@demo.org", "Welcome email", "Hi There User3.");
		CompletableFuture<Boolean> fourthEmail = emailService.sendHtmlEmail("demo.user4@demo.org", "Welcome email", "Hi There User4.");
		CompletableFuture.allOf(FirstEmail, secondEmail, thirdEmail, fourthEmail).join();
		
		log.info("send html notification controller execution finished.");
		return Collections.singletonMap("message", status);
	}

}
