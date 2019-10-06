package org.dms.middleware.app.dao.impl;

import org.dms.middleware.app.dao.UserDao;
import org.dms.middleware.app.dao.repository.UserRepository;
import org.dms.middleware.app.vo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserRepository userRepository;

	@Override
	public User addNewUser(String userName, String userFirstName, String userLastName, String userEmail,
			String userRole) {
		User user = new User();
		user.setUsername(userName);
		user.setFirstname(userFirstName);
		user.setLastname(userLastName);
		user.setEmail(userEmail);
		user.setRole(userRole);
		user = userRepository.insert(user);
		log.info("Inserted user record successfully [{}]", user);
		return user;
	}

}
