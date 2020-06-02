package org.dms.batch.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.stereotype.Component;

@Component
public class JobExecutionNotificationListener extends JobExecutionListenerSupport {

	private static final Logger log = LoggerFactory.getLogger(JobExecutionNotificationListener.class);

	@Override
	public void beforeJob(JobExecution jobExecution) {
		super.beforeJob(jobExecution);
		log.info("JOB: Name [{}], Id [{}], Status [{}]", jobExecution.getJobInstance().getJobName(),
				jobExecution.getId(), jobExecution.getStatus());
	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		super.afterJob(jobExecution);
		log.info("JOB: Name [{}], Id [{}], Status [{}]", jobExecution.getJobInstance().getJobName(),
				jobExecution.getId(), jobExecution.getStatus());
	}

}
