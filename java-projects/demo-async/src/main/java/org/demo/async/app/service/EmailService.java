package org.demo.async.app.service;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class EmailService {

	/**
	 * This method sends an email to the recipient asynchronously.
	 *
	 * @param toAddress
	 *            the recipient's email address.
	 * @param subjectText
	 *            the email's subject.
	 * @param bodyText
	 *            the email's body.
	 */
	@Async
	public void sendEmail(String toAddress, String subjectText, String bodyText) {
		try {
			log.info("sendEmail method start by {} at {}.", Thread.currentThread().getName(), LocalDateTime.now());
			Thread.sleep(10000);
			log.info("Mail sent to: [{}], Subject: [{}], Body [{}].", toAddress, subjectText, bodyText);
			log.info("sendEmail method ended by {} at {}.", Thread.currentThread().getName(), LocalDateTime.now());
		} catch (Exception e) {
			log.error("sendEmail method failed.", e);
		}
	}

	/**
	 * This method sends a html email to the recipient asynchronously.
	 *
	 * @param toAddress
	 *            the recipient's email address.
	 * @param subjectText
	 *            the email's subject.
	 * @param bodyText
	 *            the email's body.
	 * @return a CompletableFuture that completes with a boolean value indicating whether the email could be successfully sent.
	 */
	@Async
	public CompletableFuture<Boolean> sendHtmlEmail(String toAddress, String subjectText, String bodyText) {
		try {
			log.info("sendHtmlEmail method start by {} at {}.", Thread.currentThread().getName(), LocalDateTime.now());
			Thread.sleep(10000);
			log.info("Html mail sent to: [{}], Subject: [{}], Body [{}].", toAddress, subjectText, bodyText);
			log.info("sendHtmlEmail method ended by {} at {}.", Thread.currentThread().getName(), LocalDateTime.now());
		} catch (Exception e) {
			log.error("sendHtmlEmail method failed.", e);
			return CompletableFuture.completedFuture(Boolean.FALSE);
		}
		return CompletableFuture.completedFuture(Boolean.TRUE);
	}

}
