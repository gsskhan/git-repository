package org.demo.microservice.configclient;

import org.demo.microservice.configclient.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
	
	@Autowired
	private User user;

	@RequestMapping(
			value = "/userinfo", 
			method = RequestMethod.GET, 
			produces = MediaType.TEXT_PLAIN_VALUE)
	public String userInformatuion() {
		return String.format(user.toString());
	}

}
