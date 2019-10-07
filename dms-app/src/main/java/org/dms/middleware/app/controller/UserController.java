package org.dms.middleware.app.controller;

import org.dms.middleware.app.dao.repository.UserRepository;
import org.dms.middleware.app.vo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserRepository userRepository;

	@PostMapping(path = "/add", consumes = "application/json", produces = "application/json")
	public User registerNewUser(@RequestBody User user) {
		user = userRepository.insert(user);
		log.info("Added user record successfully [{}].", user);
		return user;
	}

	@GetMapping(path = "/find/{uname}", produces = "application/json")
	public User getUserDetails(@PathVariable("uname") String usrName) {
		User user = userRepository.findByUsername(usrName);
		log.info("found user record [{}].", user);
		return user;
	}

}
