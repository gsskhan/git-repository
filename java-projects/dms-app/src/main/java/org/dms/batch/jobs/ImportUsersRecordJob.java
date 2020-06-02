package org.dms.batch.jobs;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;

import org.dms.batch.config.JobExecutionNotificationListener;
import org.dms.batch.processors.UserRecordProcessor;
import org.dms.core.entities.User;
import org.dms.core.model.UserRecord;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.data.MongoItemReader;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class ImportUsersRecordJob {
	
	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Autowired
	public MongoTemplate mongoTemplate;

	@Autowired
	public EntityManagerFactory emf;

	@Bean
	public MongoItemReader<UserRecord> userRecordReader() {
		MongoItemReader<UserRecord> reader = new MongoItemReader<UserRecord>();
		reader.setTemplate(mongoTemplate);
		reader.setTargetType(UserRecord.class);
		reader.setQuery("{}");

		Map<String, Direction> sorts = new HashMap<String, Sort.Direction>(1);
		sorts.put("id", Sort.Direction.ASC);
		reader.setSort(sorts);

		return reader;
	}

	@Bean
	public UserRecordProcessor userRecordProcessor() {
		return new UserRecordProcessor();
	}

	@Bean
	public JpaItemWriter<User> userRecordWriter() {
		JpaItemWriter<User> writer = new JpaItemWriter<User>();
		writer.setEntityManagerFactory(emf);
		return writer;
	}
	
	@Bean
	public Job importUserRecordJob(JobExecutionNotificationListener listener, Step importUserRecordJobStep1) {
	  return jobBuilderFactory.get("importUserRecordJob")
	    .incrementer(new RunIdIncrementer())
	    .listener(listener)
	    .flow(importUserRecordJobStep1)
	    .end()
	    .build();
	}

	@Bean
	public Step importUserRecordJobStep1(JpaItemWriter<User> userRecordWriter) {
	  return stepBuilderFactory.get("importUserRecordJobStep1")
	    .<UserRecord, User> chunk(10)
	    .reader(userRecordReader())
	    .processor(userRecordProcessor())
	    .writer(userRecordWriter)
	    .build();
	}

}
