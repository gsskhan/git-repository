package org.dms.web.api.service;

import org.dms.web.api.entity.User;
import org.dms.web.api.exception.DmsApiException;
import org.dms.web.api.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private UserRepository userRepository;

	public User verifyUserLogin(User usr) throws DmsApiException {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean registerNewUser(User usr) throws DmsApiException {
		usr = userRepository.save(usr);
		LOGGER.info("User added successfully - {}", usr);
		return true;
	}

}
