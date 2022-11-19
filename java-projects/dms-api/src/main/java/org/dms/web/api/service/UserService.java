package org.dms.web.api.service;

import org.dms.web.api.entity.User;
import org.dms.web.api.exception.DmsApiException;
import org.dms.web.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public User verifyUserLogin(User usr) throws DmsApiException {
		// TODO Auto-generated method stub
		return null;
	}

	public User registerNewUser(User usr) throws DmsApiException {
		usr = userRepository.save(usr);
		log.info("User added successfully - {}", usr);
		return usr;
	}

}
