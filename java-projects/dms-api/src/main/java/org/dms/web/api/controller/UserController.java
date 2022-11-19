package org.dms.web.api.controller;

import static org.dms.web.api.common.AppConstants.*;

import org.dms.web.api.entity.User;
import org.dms.web.api.exception.DmsApiException;
import org.dms.web.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.Slf4j;;

@Slf4j
@RestController
@RequestMapping(USER_CONTROLLER_URI)
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping(path = POST_LOGIN_VERIFY, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public User verifyLogin(@RequestBody User usr) throws DmsApiException {
		User user = userService.verifyUserLogin(usr);
		log.info("controller completed... login verified.");
		return user;
	}

	@PostMapping(path = POST_REGISTER, consumes = MediaType.APPLICATION_JSON_VALUE)
	public User registerUser(@RequestBody User usr) throws DmsApiException {
		User newUser = userService.registerNewUser(usr);
		log.info("controller completed... new user registered.");
		return newUser;
	}

}
