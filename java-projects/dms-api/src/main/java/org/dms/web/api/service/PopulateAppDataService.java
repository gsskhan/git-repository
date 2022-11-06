package org.dms.web.api.service;

import java.util.List;

import org.dms.web.api.entity.SystemVariables;
import org.dms.web.api.repository.SystemVariablesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This class has single run() method. And its sole purpose is to populate the
 * data in database required for proper functioning of the application.
 * 
 * @author gsskhan
 *
 */

@Service
public class PopulateAppDataService {

	private static final Logger LOGGER = LoggerFactory.getLogger(PopulateAppDataService.class);

	@Autowired
	private SystemVariablesRepository systemVariablesRepository;

	public void run() {
		// Add roles
		this.populateRoles();
	}

	private void populateRoles() {
		List<SystemVariables> list = systemVariablesRepository.findByName("ROLES");

		SystemVariables examineeRoleSV = list.stream().filter(sv -> sv.getValue().equals("EXAMINEE")).findFirst()
				.orElse(null);
		if (examineeRoleSV == null) {
			list.add(new SystemVariables("ROLES", "EXAMINEE"));
		}

		SystemVariables examinerRoleSV = list.stream().filter(sv -> sv.getValue().equals("EXAMINER")).findFirst()
				.orElse(null);
		if (examinerRoleSV == null) {
			list.add(new SystemVariables("ROLES", "EXAMINER"));
		}

		SystemVariables reviewerRoleSV = list.stream().filter(sv -> sv.getValue().equals("REVIEWER")).findFirst()
				.orElse(null);
		if (reviewerRoleSV == null) {
			list.add(new SystemVariables("ROLES", "REVIEWER"));
		}

		SystemVariables AdministratorRoleSV = list.stream().filter(sv -> sv.getValue().equals("ADMINISTRATOR"))
				.findFirst().orElse(null);
		if (AdministratorRoleSV == null) {
			list.add(new SystemVariables("ROLES", "ADMINISTRATOR"));
		}

		systemVariablesRepository.saveAllAndFlush(list);
		LOGGER.info("Roles refreshed - {}", list);
	}

}
