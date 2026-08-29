package com.demo.kafka.app.controller;

import com.demo.kafka.app.producer.MessageProducer;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
public class MessageController {

    private final MessageProducer messageProducer;

    public MessageController(MessageProducer messageProducer) {
        this.messageProducer = messageProducer;
    }

    @PostMapping("/api/messages")
    public ResponseEntity<String> publish(@RequestParam @NotBlank String content) {
        messageProducer.send(content);
        return ResponseEntity.accepted().body("Message queued for publishing");
    }
}
