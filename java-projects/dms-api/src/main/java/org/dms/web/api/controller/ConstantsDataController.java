package org.dms.web.api.controller;

import java.util.List;

import static org.dms.web.api.common.ApiServiceConstants.CONSTANTS_CONTROLLER_URI;
import static org.dms.web.api.common.ApiServiceConstants.GET_ALL_SYSTEM_VARIABLES_URI;

import org.dms.web.api.entity.SystemVariables;
import org.dms.web.api.exception.DmsApiException;
import org.dms.web.api.service.ConstantsDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(CONSTANTS_CONTROLLER_URI)
public class ConstantsDataController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ConstantsDataController.class);

	@Autowired
	private ConstantsDataService constantsDataService;

	@RequestMapping(value = GET_ALL_SYSTEM_VARIABLES_URI, method = RequestMethod.GET)
	public List<SystemVariables> getAllSystemVarables() throws DmsApiException {
		List<SystemVariables> list = constantsDataService.getAll();
		LOGGER.info("controller completed... returned all system variables");
		return list;
	}

}
