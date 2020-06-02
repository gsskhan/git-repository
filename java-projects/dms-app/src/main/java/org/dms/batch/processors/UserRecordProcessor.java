package org.dms.batch.processors;

import java.util.Optional;

import org.dms.core.entities.User;
import org.dms.core.model.UserRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

public class UserRecordProcessor implements ItemProcessor<UserRecord, User> {

	private static final Logger log = LoggerFactory.getLogger(UserRecordProcessor.class);

	@Override
	public User process(UserRecord usr) throws Exception {
		User user = new User(
				Optional.ofNullable(usr.getId()).orElse(""),
				Optional.ofNullable(usr.getUserId()).orElse(null),
				Optional.ofNullable(usr.getUsername()).orElse(""),
				Optional.ofNullable(usr.getFirstname()).orElse(""),
				Optional.ofNullable(usr.getLastname()).orElse(""),
				Optional.ofNullable(usr.getEmail()).orElse(""),
				Optional.ofNullable(usr.getRole()).orElse(""),
				Optional.ofNullable(usr.getPassword()).orElse(""));
		log.info("processed ... {}", usr);
		return user;
	}

}
