package org.dms.batch.jobs;

import org.dms.core.constants.RolesEnum;
import org.dms.core.dao.repository.UserRecordRepository;
import org.dms.core.dao.repository.UserRepository;
import org.dms.core.entities.User;
import org.dms.core.model.UserRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ManageUsersJob {

	private static final Logger log = LoggerFactory.getLogger(ManageUsersJob.class);

	@Autowired
	private UserRecordRepository userRecordRepository;

	@Autowired
	private UserRepository userRepository;

	public void init() {
		this.addAdminUsers();
		this.loadUsersDataFromNoSQltoRDBMs();
	}
	
	private void loadUsersDataFromNoSQltoRDBMs() {
		long taskStartTime = System.currentTimeMillis();
		
		long taskEndTime = System.currentTimeMillis();
		log.info("loaded users data from NoSQL to RDMBs. duration {} milliseconds ...", (taskEndTime - taskStartTime));
	}
	
	private void addAdminUsers() {
		long taskStartTime = System.currentTimeMillis();
		UserRecord userRecord = userRecordRepository.findByUsername("admin");
		User user = userRepository.findByUsername("admin");

		if (userRecord == null) {
			userRecord = new UserRecord(null, null, "admin", "Super", "Administrator", "admin@dms.org",
					RolesEnum.SYSADMIN.name(), "password");
			userRecord = userRecordRepository.save(userRecord);
			log.debug("admin user record added in NoSQL. {}", userRecord);
		} else {
			userRecord.setFirstname("Super");
			userRecord.setLastname("Administrator");
			userRecord.setEmail("admin@dms.org");
			userRecord.setRole(RolesEnum.SYSADMIN.name());
			userRecord.setPassword("password");
			userRecord = userRecordRepository.save(userRecord);
			log.debug("admin user record updated in NoSQL. {}", userRecord);
		}

		if (user == null) {
			user = new User(userRecord.getId(), null, "admin", "Super", "Administrator", "admin@dms.org",
					RolesEnum.SYSADMIN.name(), "password");
			user = userRepository.save(user);
			log.debug("admin user record added in RDBMs. {}", user);
		} else {
			user.setNosqlId(userRecord.getId());
			user.setFirstName("Super");
			user.setLastName("Administrator");
			user.setEmail("admin@dms.org");
			user.setRole(RolesEnum.SYSADMIN.name());
			user.setPassword("password");
			user = userRepository.save(user);
			log.debug("admin user record updated in RDMBs. {}", userRecord);
		}

		userRecord.setUserId(user.getUserId());
		userRecord = userRecordRepository.save(userRecord);
		long taskEndTime = System.currentTimeMillis();
		log.info("admin user record synced in both NoSQL & RDMBs. duration {} milliseconds ...", (taskEndTime - taskStartTime));
	}

}
