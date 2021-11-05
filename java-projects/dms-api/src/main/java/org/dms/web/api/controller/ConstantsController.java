package org.dms.web.api.controller;

import java.util.List;

import static org.dms.web.api.common.ApiServiceConstants.CONSTANTS_CONTROLLER_URI;
import static org.dms.web.api.common.ApiServiceConstants.GET_ALL_SYSTEM_VARIABLES_URI;
import static org.dms.web.api.common.ApiServiceConstants.GET_SYSTEM_VARIABLES_BY_NAME_URI;
import static org.dms.web.api.common.ApiServiceConstants.GET_SYSTEM_VARIABLES_BY_VALUE_URI;
import static org.dms.web.api.common.ApiServiceConstants.GET_SYSTEM_VARIABLES_BY_ID_URI;
import static org.dms.web.api.common.ApiServiceConstants.POST_ADD_SYSTEM_VARIABLES;

import org.dms.web.api.entity.SystemVariables;
import org.dms.web.api.exception.DmsApiException;
import org.dms.web.api.service.ConstantsDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(CONSTANTS_CONTROLLER_URI)
public class ConstantsController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ConstantsController.class);

	@Autowired
	private ConstantsDataService constantsDataService;

	@RequestMapping(value = GET_ALL_SYSTEM_VARIABLES_URI, method = RequestMethod.GET)
	public List<SystemVariables> getAllSystemVariables() throws DmsApiException {
		List<SystemVariables> list = constantsDataService.getAll();
		LOGGER.info("controller completed... returned all system variables");
		return list;
	}

	@RequestMapping(value = GET_SYSTEM_VARIABLES_BY_NAME_URI, method = RequestMethod.GET)
	public List<SystemVariables> getSystemVariablesByName(@PathVariable(name = "name") String name)
			throws DmsApiException {
		List<SystemVariables> list = constantsDataService.getByName(name);
		LOGGER.info("controller completed... returned all system variables");
		return list;
	}

	@RequestMapping(value = GET_SYSTEM_VARIABLES_BY_VALUE_URI, method = RequestMethod.GET)
	public List<SystemVariables> getSystemVariablesByValue(@PathVariable(name = "value") String value)
			throws DmsApiException {
		List<SystemVariables> list = constantsDataService.getByValue(value);
		LOGGER.info("controller completed... returned all system variables");
		return list;
	}

	@RequestMapping(value = GET_SYSTEM_VARIABLES_BY_ID_URI, method = RequestMethod.GET)
	public SystemVariables getSystemVariablesById(@PathVariable(name = "id") Long id) throws DmsApiException {
		SystemVariables sv = constantsDataService.getById(id);
		LOGGER.info("controller completed... returned all system variables");
		return sv;
	}

	@RequestMapping(value = POST_ADD_SYSTEM_VARIABLES, method = RequestMethod.POST)
	public void getSystemVariablesById(@RequestBody SystemVariables sv) throws DmsApiException {
		constantsDataService.addOrUpdate(sv);
		LOGGER.info("controller completed... saved or updated system variable.");
	}
}
