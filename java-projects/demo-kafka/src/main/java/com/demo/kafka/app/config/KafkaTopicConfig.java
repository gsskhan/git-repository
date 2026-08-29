package com.demo.kafka.app.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

/**
 * Auto-provisions the demo topic on startup (via KafkaAdmin, which Spring Boot
 * autoconfigures from spring.kafka.bootstrap-servers). Handy for local/dev use;
 * for production, topics are typically managed externally.
 */
@Configuration
public class KafkaTopicConfig {

    @Value("${app.kafka.topic.demo}")
    private String demoTopic;

    @Bean
    public NewTopic demoTopic() {
        return TopicBuilder.name(demoTopic)
                .partitions(3)
                .replicas(1)
                .build();
    }
}
