# kafka-demo

Minimal Spring Boot 4.1.0 project with a Kafka producer, consumer, and configuration.

## Requirements
- JDK 21+ (Spring Boot 4.x requires a modern JDK baseline)
- Maven 3.9+
- A running Kafka broker (see below for a quick local one via Docker)

## Project layout
```
src/main/java/com/example/kafkademo/
├── KafkaDemoApplication.java        # main entry point
├── config/
│   ├── KafkaProducerConfig.java     # ProducerFactory + KafkaTemplate beans
│   ├── KafkaConsumerConfig.java     # ConsumerFactory + listener container factory
│   └── KafkaTopicConfig.java        # auto-creates the demo topic on startup
├── producer/
│   └── MessageProducer.java         # sends Message objects to Kafka
├── consumer/
│   └── MessageConsumer.java         # @KafkaListener, manual ack, error handling
├── controller/
│   └── MessageController.java       # POST /api/messages?content=... to publish
└── model/
    └── Message.java                 # record sent/received as JSON
```

## Run a local Kafka broker (Docker)
```bash
docker run -d --name kafka-broker -p 9092:9092 apache/kafka:latest
```
Ensure a topic named as "my-test-topic" is created.
```bash
docker exec -it kafka-broker /opt/kafka/bin/kafka-topics.sh \
			  --create \
			  --bootstrap-server localhost:9092 \
			  --replication-factor 1 \
			  --partitions 3 \
			  --topic my-test-topic
```

## Build & run
```bash
mvn clean package
mvn spring-boot:run
# or
java -jar target/kafka-demo-0.0.1-SNAPSHOT.jar
```

## Try it out
Publish a message:
```bash
curl -X POST "http://localhost:8080/api/messages?content=hello-kafka"
```
Watch the application logs — `MessageProducer` logs the send acknowledgment
and `MessageConsumer` logs the received record a moment later.

### Swagger UI
Once the app is running, open:
- Swagger UI: http://localhost:8080/swagger-ui.html
- Raw OpenAPI spec (JSON): http://localhost:8080/v3/api-docs

You can trigger `POST /api/messages` directly from the Swagger UI page.

## Notes
- Producer uses `JsonSerializer`, consumer uses `JsonDeserializer` wrapped in
  `ErrorHandlingDeserializer` so bad/poison messages don't crash the listener container.
- Consumer uses `AckMode.MANUAL_IMMEDIATE` — offsets only commit after your
  listener code finishes successfully.
- `DefaultErrorHandler` retries a failing record twice (1s backoff) before giving up.
- `KafkaTopicConfig` auto-creates `demo-topic` (3 partitions) on startup — remove
  this in favor of externally managed topics for production use.
- Update `pom.xml`'s parent version if a newer patch of Spring Boot 4.1.x is
  available by the time you build this.
- Swagger UI is provided by `springdoc-openapi-starter-webmvc-ui` (v3.x line
  is the Spring Boot 4–compatible major version). Check for a newer patch
  release before building for production.
