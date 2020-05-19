package org.dms.middleware.app.integration.test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.dms.middleware.app.DmsAppApplicationTests;
import org.dms.middleware.app.constants.RolesEnum;
import org.dms.middleware.app.dao.repository.UserRepository;
import org.dms.middleware.app.vo.User;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class IntegrationTest extends DmsAppApplicationTests {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserRepository userRepository;

	@Test
	public void dummy() {
	}

	@Test
	public void add_Data_For_Integration_Test() {
		User user = userRepository.findByUsername("tester1");
		if (user == null) {			
			user = new User(null, "tester1", "Tester", "T1", "tester1@dms.org", RolesEnum.SYSADMIN.toString(),
					"password");
			log.info("Added new test user record successfully [{}].", user);
		} else {
			user.setPassword("password");
			user = userRepository.save(user);
			log.info("Updated test user record successfully [{}].", user);			
		}		
		assertNotNull(user.getId());
	}

}
