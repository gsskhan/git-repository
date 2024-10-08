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

	private final UserRepository userRepository;

	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	/**
	 * Verify if the given user exists in the database with the given password.
	 * 
	 * @param usr The user object with username and password to verify.
	 * @return The existing user object if the user exists and the password is correct.
	 * @throws DmsApiException If the user does not exist or the password is incorrect.
	 */
	public User verifyUserLogin(User usr) throws DmsApiException {
		// Check if the user exists in the database
		User existingUser = userRepository.findByUserNameAndPassword(usr.getUserName(), usr.getPassword());
		
		if (existingUser != null) {
			// User exists, return the existing user object
			return existingUser;
		} else {
			// User does not exist, throw an exception
			throw new DmsApiException("Invalid username or password");
		}
	}

	/**
	 * Registers a new user and persists it in the database.
	 * 
	 * @param usr The user object to register. The id field is ignored.
	 * @return The registered user object with the id field populated.
	 * @throws DmsApiException If the user already exists or if any other unexpected error occurs.
	 */
	public User registerNewUser(User usr) throws DmsApiException {
		usr = userRepository.save(usr);
		log.info("User added successfully - {}", usr);
		return usr;
	}

}
