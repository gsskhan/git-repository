package org.demo.rabitmq;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class MessageSender {

	private static Logger log = LoggerFactory.getLogger(MessageSender.class);
	
	private final static String QUEUE_NAME = "sample-queue";
	
	public static void main(String[] args) {
		log.info("Message sender program started ...");
		try {
			ConnectionFactory factory = new ConnectionFactory();
		    factory.setHost("localhost");
		    factory.setPort(5672);
		    factory.setUsername("guest");
		    factory.setPassword("guest");
		    Connection connection = factory.newConnection();
		    Channel channel = connection.createChannel();

		    channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		    String message = "Hello World!";
		    channel.basicPublish("", QUEUE_NAME, null, message.getBytes("UTF-8"));
		    log.info("[x] Sent '" + message + "'");

		    channel.close();
		    connection.close();
			
		} catch (IOException | TimeoutException e) {
			log.error("error sending to rabitmq ...", e);
		} 
		
	}

}
