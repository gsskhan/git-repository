package org.dms.batch.scheduler;

import org.dms.batch.jobs.ManageUsersJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class BatchScheduler {

	private static final Logger log = LoggerFactory.getLogger(BatchScheduler.class);
		
	@Autowired
	private ManageUsersJob manageUsersJOB;
	
	@Scheduled(fixedDelay = 900000)
	public void init() {
		log.info("BATCH SCHEDULER STARTED **************");	

		manageUsersJOB.init();
		log.info("Job 1: users data synchronization done");	
		
		log.info("BATCH SCHEDULER COMPLETED ************");		
	}

}
