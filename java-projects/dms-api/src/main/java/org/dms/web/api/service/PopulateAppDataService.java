package org.dms.web.api.service;

import java.util.*;

import org.dms.web.api.entity.*;
import org.dms.web.api.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

/**
 * This class has single run() method. And its sole purpose is to populate the
 * data in database required for proper functioning of the application.
 * 
 * @author gsskhan
 *
 */

@Slf4j
@Service
public class PopulateAppDataService {

	
	private final RoleRepository roleRepository;

	@Autowired
	public PopulateAppDataService(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}

	/**
	 * Runs the population of the application data. This method is to be called only
	 * once when the application is started. It populates the required data in the
	 * database tables.
	 */
	public void run() {
		// Add roles
		this.populateRoles();
	}

	/**
	 * Populates the ROLES table with default roles required by the application to
	 * function properly. If the roles are already present in the table, then it
	 * simply ignores and does nothing.
	 */
	private void populateRoles() {
		List<Role> roleList = Optional.ofNullable(roleRepository.getAllRoles()).orElse(new ArrayList<>());

		Optional<Role> examinee = roleList.stream().filter(r -> r.getRoleName().equalsIgnoreCase("EXAMINEE"))
				.findFirst();
		if (examinee.isEmpty()) {
			roleList.add(new Role("EXAMINEE"));
		}

		Optional<Role> examiner = roleList.stream().filter(r -> r.getRoleName().equalsIgnoreCase("EXAMINER"))
				.findFirst();
		if (examiner.isEmpty()) {
			roleList.add(new Role("EXAMINER"));
		}

		Optional<Role> reviewer = roleList.stream().filter(r -> r.getRoleName().equalsIgnoreCase("REVIEWER"))
				.findFirst();
		if (reviewer.isEmpty()) {
			roleList.add(new Role("REVIEWER"));
		}

		Optional<Role> admin = roleList.stream().filter(r -> r.getRoleName().equalsIgnoreCase("ADMINISTRATOR"))
				.findFirst();
		if (admin.isEmpty()) {
			roleList.add(new Role("ADMINISTRATOR"));
		}

		roleList = roleRepository.saveAllAndFlush(roleList);
		log.info("{} roles saved in ROLES Table.", roleList.size());

	}

}
