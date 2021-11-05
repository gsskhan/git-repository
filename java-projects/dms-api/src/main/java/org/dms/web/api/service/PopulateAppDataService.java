package org.dms.web.api.service;

import java.util.List;

import org.dms.web.api.dao.SystemVariableDao;
import org.dms.web.api.entity.SystemVariable;
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
	private SystemVariableDao systemVariableDao;

	public void run() {
		// Add roles
		this.populateRoles();
	}

	private void populateRoles() {
		List<SystemVariable> list = systemVariableDao.findByName("ROLES");

		SystemVariable examineeRoleSV = list.stream().filter(sv -> sv.getValue().equals("EXAMINEE")).findFirst()
				.orElse(null);
		if (examineeRoleSV == null) {
			list.add(new SystemVariable("ROLES", "EXAMINEE"));
		}

		SystemVariable examinerRoleSV = list.stream().filter(sv -> sv.getValue().equals("EXAMINER")).findFirst()
				.orElse(null);
		if (examinerRoleSV == null) {
			list.add(new SystemVariable("ROLES", "EXAMINER"));
		}

		SystemVariable reviewerRoleSV = list.stream().filter(sv -> sv.getValue().equals("REVIEWER")).findFirst()
				.orElse(null);
		if (reviewerRoleSV == null) {
			list.add(new SystemVariable("ROLES", "REVIEWER"));
		}

		SystemVariable AdministratorRoleSV = list.stream().filter(sv -> sv.getValue().equals("ADMINISTRATOR"))
				.findFirst().orElse(null);
		if (AdministratorRoleSV == null) {
			list.add(new SystemVariable("ROLES", "ADMINISTRATOR"));
		}

		systemVariableDao.saveAllAndFlush(list);
		LOGGER.info("Roles refreshed - {}", list);
	}

}
