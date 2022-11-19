package org.dms.web.api.controller;

import java.util.List;

import static org.dms.web.api.common.AppConstants.*;

import org.dms.web.api.entity.SystemVariable;
import org.dms.web.api.exception.DmsApiException;
import org.dms.web.api.service.ConstantsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(CONSTANTS_CONTROLLER_URI)
public class ConstantsController {

	@Autowired
	private ConstantsService constantsDataService;

	@GetMapping(path = GET_ALL_SYSTEM_VARIABLES_URI, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<SystemVariable> getAllSystemVariables() throws DmsApiException {
		List<SystemVariable> list = constantsDataService.getAll();
		log.info("controller completed... returned all system variables");
		return list;
	}

	@GetMapping(path = GET_SYSTEM_VARIABLES_BY_NAME_URI, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<SystemVariable> getSystemVariablesByName(@PathVariable(name = "name") String name)
			throws DmsApiException {
		List<SystemVariable> list = constantsDataService.getByName(name);
		log.info("controller completed... returned all system variables");
		return list;
	}

	@GetMapping(path = GET_SYSTEM_VARIABLES_BY_VALUE_URI, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<SystemVariable> getSystemVariablesByValue(@PathVariable(name = "value") String value)
			throws DmsApiException {
		List<SystemVariable> list = constantsDataService.getByValue(value);
		log.info("controller completed... returned all system variables");
		return list;
	}

	@GetMapping(path = GET_SYSTEM_VARIABLES_BY_ID_URI, produces = MediaType.APPLICATION_JSON_VALUE)
	public SystemVariable getSystemVariablesById(@PathVariable(name = "id") Long id) throws DmsApiException {
		SystemVariable sv = constantsDataService.getById(id);
		log.info("controller completed... returned all system variables");
		return sv;
	}

	@PostMapping(path = POST_ADD_SYSTEM_VARIABLES, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void addOrUpdateSystemVariables(@RequestBody SystemVariable sv) throws DmsApiException {
		constantsDataService.addOrUpdate(sv);
		log.info("controller completed... saved or updated system variable.");
	}
}
