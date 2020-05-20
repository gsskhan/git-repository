package org.demo.rabitmq;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rabbitmq.client.*;

public class MessageReciever {

	private static Logger log = LoggerFactory.getLogger(MessageReciever.class);

	private final static String QUEUE_NAME = "sample-queue";

	public static void main(String[] args) {
		try {
			ConnectionFactory factory = new ConnectionFactory();
			factory.setHost("localhost");
			factory.setPort(5672);
			factory.setUsername("guest");
			factory.setPassword("guest");
			Connection connection = factory.newConnection();
			Channel channel = connection.createChannel();

			channel.queueDeclare(QUEUE_NAME, false, false, false, null);
			log.info(" [*] Waiting for messages. To exit press CTRL+C");

			Consumer consumer = new DefaultConsumer(channel) {
				@Override
				public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
						byte[] body) throws IOException {
					String message = new String(body, "UTF-8");
					System.out.println(" [x] Received '" + message + "'");
				}
			};
			channel.basicConsume(QUEUE_NAME, true, consumer);
		} catch (Exception e) {
			log.error("error recieving from rabitmq ...", e);
		}

	}

}
