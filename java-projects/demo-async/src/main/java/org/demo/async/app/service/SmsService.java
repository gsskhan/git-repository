package org.demo.async.app.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SmsService {

    public void sendSms(String toAddress, String subjectText, String bodyText) {
        try {
            log.info("sendSms method start by {} at {}", Thread.currentThread(), LocalDateTime.now());
            Thread.sleep(10000);
            log.info("SMS sent to {}, Subject: {}, Body [{}].", toAddress, subjectText, bodyText);
            log.info("sendSms method ended by {} at {}.", Thread.currentThread(), LocalDateTime.now());

        } catch (Exception e) {
            log.error("sendSms method failed.", e);
        }
    }
}