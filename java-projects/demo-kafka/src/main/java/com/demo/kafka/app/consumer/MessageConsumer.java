package com.demo.kafka.app.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumer {

    private static final Logger log = LoggerFactory.getLogger(MessageConsumer.class);

    @KafkaListener(
            topics = "${app.kafka.topic.demo}",
            groupId = "${app.kafka.consumer.group-id}",
            autoStartup = "true"
    )
    public void listen(ConsumerRecord<String, String> record, Acknowledgment ack) {
        try {
            log.info("############### Message processing started ####################");
            String content = record.value();
            log.info("Received message key={} content='{}' partition={} offset={} topic={}",
                    record.key(), content, record.partition(), record.offset(), record.topic());

            // ... business logic goes here ...

            ack.acknowledge(); // commit offset only after successful processing
        } catch (Exception ex) {
            log.error("Error processing record at offset={}", record.offset(), ex);
            throw ex; // let the container's error handler decide retry/skip
        } finally {
            log.info("############### Message processing finished ###################");
        }
    }
}
