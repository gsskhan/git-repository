package org.dms.middleware.app.controller;

import static org.dms.middleware.app.constants.AppConstants.*;
import org.dms.middleware.app.dao.repository.UserRecordRepository;
import org.dms.middleware.app.domain.model.UserRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping(API_VERSION + USER_CONTROLLER_URI)
public class UserController {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserRecordRepository userRepository;

	@PostMapping(path = USER_CONTROLLER_ADD_USER, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public UserRecord registerNewUser(@RequestBody UserRecord user) {
		user = userRepository.insert(user);
		log.info("Added user record successfully [{}].", user);
		return user;
	}

	@GetMapping(path = USER_CONTROLLER_FIND_BY_USERNAME, produces = MediaType.APPLICATION_JSON_VALUE)
	public UserRecord getUserDetails(@PathVariable("uname") String usrName) {
		UserRecord user = userRepository.findByUsername(usrName);
		log.info("Found user record [{}].", user);
		return user;
	}

}