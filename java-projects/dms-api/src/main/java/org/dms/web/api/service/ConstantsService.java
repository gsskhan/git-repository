package org.dms.web.api.service;

import java.util.List;

import org.dms.web.api.dao.SystemVariableDao;
import org.dms.web.api.entity.SystemVariable;
import org.dms.web.api.exception.DmsApiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConstantsService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ConstantsService.class);

	@Autowired
	private SystemVariableDao systemVariablesRepository;

	public List<SystemVariable> getAll() throws DmsApiException {
		List<SystemVariable> list = systemVariablesRepository.findAll();
		LOGGER.info("Found {} system variables.", list.stream().count());
		return list;
	}

	public SystemVariable getById(Long id) throws DmsApiException {
		SystemVariable sv = systemVariablesRepository.findById(id).orElse(null);
		LOGGER.info("Found by id - {}.", sv);
		return sv;
	}

	public List<SystemVariable> getByName(String name) throws DmsApiException {
		List<SystemVariable> list = systemVariablesRepository.findByName(name);
		LOGGER.info("Found {} system variables.", list.stream().count());
		return list;
	}

	public List<SystemVariable> getByValue(String value) throws DmsApiException {
		List<SystemVariable> list = systemVariablesRepository.findByValue(value);
		LOGGER.info("Found {} system variables.", list.stream().count());
		return list;
	}

	public SystemVariable addOrUpdate(SystemVariable sv) throws DmsApiException {
		// Check for inputs to be null or empty.
		if (sv == null) {
			throw new DmsApiException("No data to add or update.");
		}
		if (sv.getName() == null || sv.getName().isEmpty()) {
			throw new DmsApiException("Name cannot be empty.");
		}
		
		// check if record already exists in db.
		SystemVariable svTemp = null;
		if (sv.getId() == null) {
			svTemp = systemVariablesRepository.findByNameAndValue(sv.getName(), sv.getValue()).stream().findFirst().orElse(null);
		} else {
			svTemp = systemVariablesRepository.findById(sv.getId()).orElse(null);
		}
		
		// Add or Update
		svTemp = systemVariablesRepository.saveAndFlush(new SystemVariable(sv.getName(), sv.getValue()));
		LOGGER.info("Added/Updated to system variables- {}.", sv);
		return svTemp;
	}

}
