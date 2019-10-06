package org.dms.middleware.app.dao.impl;

import static org.junit.Assert.assertNotNull;

import org.dms.middleware.app.DmsAppApplicationTests;
import org.dms.middleware.app.dao.impl.UserDaoImpl;
import org.dms.middleware.app.vo.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class UserDaoImplTest extends DmsAppApplicationTests {

	@Autowired
	private UserDaoImpl userDao;

	@Test
	public void testAddNewUser() {
		User u = userDao.addNewUser("tester1", "Tester 1", "Dms", "dms.tester1@gmail.com", "ADMIN");
		assertNotNull(u.getId());
	}

}
