package org.dms.middleware.app.integration.test;

import static org.junit.Assert.assertNotNull;

import org.dms.middleware.app.DmsAppApplicationTests;
import org.dms.middleware.app.constants.RolesEnum;
import org.dms.middleware.app.dao.repository.UserRepository;
import org.dms.middleware.app.vo.User;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class IntegrationTest extends DmsAppApplicationTests {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UserRepository userRepository;
	
	@Test
	public void dummy() { }

	//@Test
	public void add_Data_For_Integration_Test() {
		User user = new User();
		user.setUsername("tester1");
		user.setFirstname("Tester");
		user.setLastname("T1");
		user.setEmail("tester1@dms.org");
		user.setRole(RolesEnum.SYSADMIN.toString());
		user = userRepository.save (user);
		log.info("Inserted user record successfully [{}].", user);
		assertNotNull(user.getId());
	}

}
