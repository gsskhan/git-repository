package com.demo.kafka.app.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class MessageProducer {

    private static final Logger log = LoggerFactory.getLogger(MessageProducer.class);

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Value("${app.kafka.topic.demo}")
    private String demoTopic;

    public MessageProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(String content) {
        String key = UUID.randomUUID().toString();

        kafkaTemplate.send(demoTopic, key, content)
                .whenComplete((result, ex) -> {
                    if (ex == null) {
                        log.info("Sent message key={} to topic={} partition={} offset={}",
                                key,
                                result.getRecordMetadata().topic(),
                                result.getRecordMetadata().partition(),
                                result.getRecordMetadata().offset());
                    } else {
                        log.error("Failed to send message key={}", key, ex);
                    }
                });
    }
}
