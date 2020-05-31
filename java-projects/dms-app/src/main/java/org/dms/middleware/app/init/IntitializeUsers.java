package org.dms.middleware.app.init;

import org.dms.middleware.app.constants.RolesEnum;
import org.dms.middleware.app.dao.repository.UserRecordRepository;
import org.dms.middleware.app.dao.repository.UserRepository;
import org.dms.middleware.app.domain.entities.User;
import org.dms.middleware.app.domain.model.UserRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IntitializeUsers {

	private static final Logger log = LoggerFactory.getLogger(IntitializeUsers.class);

	@Autowired
	private UserRecordRepository userRecordRepository;

	@Autowired
	private UserRepository userRepository;

	public IntitializeUsers() {
		//this.addAdminUsers();
	}

	private void addAdminUsers() {
		UserRecord userRecord = userRecordRepository.findByUsername("admin");
		User user = userRepository.findByUsername("admin");

		if (userRecord == null) {
			userRecord = new UserRecord(null, null, "admin", "Super", "Administrator", "admin@dms.org",
					RolesEnum.SYSADMIN.toString(), "password");
			userRecord = userRecordRepository.save(userRecord);
			log.info("Admin user record added in NoSQL. {}", userRecord);
		} else {
			userRecord.setFirstname("Super");
			userRecord.setLastname("Administrator");
			userRecord.setEmail("admin@dms.org");
			userRecord.setRole(RolesEnum.SYSADMIN.toString());
			userRecord.setPassword("password");
			userRecord = userRecordRepository.save(userRecord);
			log.info("Admin user record updated in NoSQL. {}", userRecord);
		}

		if (user == null) {
			user = new User(userRecord.getId(), null, "admin", "Super", "Administrator", "admin@dms.org",
					RolesEnum.SYSADMIN.toString(), "password");
			user = userRepository.save(user);
			log.info("Admin user record added in RDBMs. {}", user);
		} else {
			user.setNosqlId(userRecord.getId());
			user.setFirstName("Super");
			user.setLastName("Administrator");
			user.setEmail("admin@dms.org");
			user.setRole(RolesEnum.SYSADMIN.toString());
			user.setPassword("password");
			user = userRepository.save(user);
			log.info("Admin user record updated in RDMBs. {}", userRecord);
		}

		userRecord.setUserId(user.getUserId());
		userRecord = userRecordRepository.save(userRecord);
		log.info("Admin user record synced in NoSQL and RDMBs both.");
	}

}
