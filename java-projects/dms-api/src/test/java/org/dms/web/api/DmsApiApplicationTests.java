package org.dms.web.api;

import org.dms.web.api.service.PopulateAppDataService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DmsApiApplicationTests {

	@Autowired
	PopulateAppDataService populateAppDataService;

	@Test
	void contextLoads() {
		/**
		 * This method has been called as test to populate application important data
		 * required for its functioning. Ideally has to be done by a db script.
		 */
		populateAppDataService.run();
	}

}
