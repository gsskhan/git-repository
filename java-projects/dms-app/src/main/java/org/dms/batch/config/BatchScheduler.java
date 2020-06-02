package org.dms.batch.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class BatchScheduler {

	private static final Logger log = LoggerFactory.getLogger(BatchScheduler.class);

	@Scheduled(fixedDelay = 900000)
	public void init() {
		log.info("BATCH SCHEDULER STARTED **************");
		log.info("BATCH SCHEDULER COMPLETED ************");
	}

}
