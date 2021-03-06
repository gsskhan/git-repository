package org.dms.middleware.app.integration.test;

import static org.junit.jupiter.api.Assertions.assertNull;

import org.dms.core.dao.repository.UserRecordRepository;
import org.dms.core.dao.repository.UserRepository;
import org.dms.core.entities.User;
import org.dms.core.model.UserRecord;
import org.dms.middleware.app.DmsAppApplicationTests;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class IntegrationTest extends DmsAppApplicationTests {

	private static final Logger log = LoggerFactory.getLogger(IntegrationTest.class);

	@Autowired
	private UserRecordRepository userRecordRepository;

	@Autowired
	private UserRepository userRepository;

	@Test
	public void dummy() {
	}

	@Test
	public void test_mongo() {
		UserRecord user = userRecordRepository.findByUsername("XXXXDUMMYXXXXXYYY");
		log.info("Result = {}", user);
		assertNull(user);
	}

	@Test
	public void test_derby() {
		User user = userRepository.findByUsername("XXXXDUMMYXXXXXYYY");
		log.info("Result = {}", user);
		assertNull(user);
	}

}
