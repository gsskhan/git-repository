package org.demo.async.app.config;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class AsyncConfig {

	public static final String TASK_EXECUTOR = "taskExecutor";

	public static final String VIRTUAL_THREAD_TASK_EXECUTOR = "virtualThreadTaskExecutor";

	
	/*
	 * To make a virual thread executor.
	 */
	@Bean(name = VIRTUAL_THREAD_TASK_EXECUTOR)
	public Executor virtualThreadTaskExecutor() {
		log.info("Configuring virtual thread task executor.");
		return Executors.newVirtualThreadPerTaskExecutor();
	}

	/*
	 * To make the virtul thread executor as default Async Executor.
	 */
	@Bean(name = TASK_EXECUTOR)
	public Executor taskExecutor() {
		log.info("Configuring task executor.");
		return virtualThreadTaskExecutor();
	}

}
