package org.dms.web.api.controller;

import java.util.List;

import static org.dms.web.api.common.AppConstants.CONSTANTS_CONTROLLER_URI;
import static org.dms.web.api.common.AppConstants.GET_ALL_SYSTEM_VARIABLES_URI;
import static org.dms.web.api.common.AppConstants.GET_SYSTEM_VARIABLES_BY_NAME_URI;
import static org.dms.web.api.common.AppConstants.GET_SYSTEM_VARIABLES_BY_VALUE_URI;
import static org.dms.web.api.common.AppConstants.GET_SYSTEM_VARIABLES_BY_ID_URI;
import static org.dms.web.api.common.AppConstants.POST_ADD_SYSTEM_VARIABLES;

import org.dms.web.api.entity.SystemVariables;
import org.dms.web.api.exception.DmsApiException;
import org.dms.web.api.service.ConstantsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(CONSTANTS_CONTROLLER_URI)
public class ConstantsController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ConstantsController.class);

	@Autowired
	private ConstantsService constantsDataService;

	@GetMapping(path = GET_ALL_SYSTEM_VARIABLES_URI, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<SystemVariables> getAllSystemVariables() throws DmsApiException {
		List<SystemVariables> list = constantsDataService.getAll();
		LOGGER.info("controller completed... returned all system variables");
		return list;
	}

	@GetMapping(path = GET_SYSTEM_VARIABLES_BY_NAME_URI, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<SystemVariables> getSystemVariablesByName(@PathVariable(name = "name") String name)
			throws DmsApiException {
		List<SystemVariables> list = constantsDataService.getByName(name);
		LOGGER.info("controller completed... returned all system variables");
		return list;
	}

	@GetMapping(path = GET_SYSTEM_VARIABLES_BY_VALUE_URI, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<SystemVariables> getSystemVariablesByValue(@PathVariable(name = "value") String value)
			throws DmsApiException {
		List<SystemVariables> list = constantsDataService.getByValue(value);
		LOGGER.info("controller completed... returned all system variables");
		return list;
	}

	@GetMapping(path = GET_SYSTEM_VARIABLES_BY_ID_URI, produces = MediaType.APPLICATION_JSON_VALUE)
	public SystemVariables getSystemVariablesById(@PathVariable(name = "id") Long id) throws DmsApiException {
		SystemVariables sv = constantsDataService.getById(id);
		LOGGER.info("controller completed... returned all system variables");
		return sv;
	}

	@PostMapping(path = POST_ADD_SYSTEM_VARIABLES, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void addOrUpdateSystemVariables(@RequestBody SystemVariables sv) throws DmsApiException {
		constantsDataService.addOrUpdate(sv);
		LOGGER.info("controller completed... saved or updated system variable.");
	}
}
