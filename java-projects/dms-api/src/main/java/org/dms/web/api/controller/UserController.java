package org.dms.web.api.controller;

import static org.dms.web.api.common.AppConstants.USER_CONTROLLER_URI;
import static org.dms.web.api.common.AppConstants.POST_LOGIN_VERIFY;
import static org.dms.web.api.common.AppConstants.POST_REGISTER;

import org.dms.web.api.entity.User;
import org.dms.web.api.exception.DmsApiException;
import org.dms.web.api.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(USER_CONTROLLER_URI)
public class UserController {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@PostMapping(path = POST_LOGIN_VERIFY, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public User verifyLogin(@RequestBody User usr) throws DmsApiException {
		User user = userService.verifyUserLogin(usr);
		LOGGER.info("controller completed... login verified.");
		return user;
	}

	@PostMapping(path = POST_REGISTER, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void registerUser(@RequestBody User usr) throws DmsApiException {
		userService.registerNewUser(usr);
		LOGGER.info("controller completed... new user registered.");
	}

}
