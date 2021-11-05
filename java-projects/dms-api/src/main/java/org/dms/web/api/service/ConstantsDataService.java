package org.dms.web.api.service;

import java.util.List;

import org.dms.web.api.dao.SystemVariablesDao;
import org.dms.web.api.entity.SystemVariables;
import org.dms.web.api.exception.DmsApiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConstantsDataService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ConstantsDataService.class);

	@Autowired
	private SystemVariablesDao systemVariablesRepository;

	public List<SystemVariables> getAll() throws DmsApiException {
		List<SystemVariables> list = systemVariablesRepository.findAll();
		LOGGER.info("Found {} system variables.", list.stream().count());
		return list;
	}

	public SystemVariables getById(Long id) throws DmsApiException {
		SystemVariables sv = systemVariablesRepository.findById(id).orElse(null);
		LOGGER.info("Found by id - {}.", sv);
		return sv;
	}

	public List<SystemVariables> getByName(String name) throws DmsApiException {
		List<SystemVariables> list = systemVariablesRepository.findByName(name);
		LOGGER.info("Found {} system variables.", list.stream().count());
		return list;
	}

	public List<SystemVariables> getByValue(String value) throws DmsApiException {
		List<SystemVariables> list = systemVariablesRepository.findByValue(value);
		LOGGER.info("Found {} system variables.", list.stream().count());
		return list;
	}

	public SystemVariables addOrUpdate(SystemVariables sv) throws DmsApiException {
		// Check for inputs to be null or empty.
		if (sv == null) {
			throw new DmsApiException("No data to add or update.");
		}
		if (sv.getName() == null || sv.getName().isEmpty()) {
			throw new DmsApiException("Name cannot be empty.");
		}
		
		// check if record already exists in db.
		SystemVariables svTemp = null;
		if (sv.getId() == null) {
			svTemp = systemVariablesRepository.findByNameAndValue(sv.getName(), sv.getValue()).stream().findFirst().orElse(null);
		} else {
			svTemp = systemVariablesRepository.findById(sv.getId()).orElse(null);
		}
		
		// Add or Update
		svTemp = systemVariablesRepository.saveAndFlush(new SystemVariables(sv.getName(), sv.getValue()));
		LOGGER.info("Added/Updated to system variables- {}.", sv);
		return svTemp;
	}

}
