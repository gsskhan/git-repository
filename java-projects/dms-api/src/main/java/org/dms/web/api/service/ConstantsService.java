package org.dms.web.api.service;

import java.util.List;

import org.dms.web.api.entity.SystemVariable;
import org.dms.web.api.exception.DmsApiException;
import org.dms.web.api.repository.SystemVariableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ConstantsService {
	
	@Autowired
	private SystemVariableRepository systemVariablesRepository;

	public List<SystemVariable> getAll() throws DmsApiException {
		List<SystemVariable> list = systemVariablesRepository.findAll();
		log.info("Found {} system variables.", list.stream().count());
		return list;
	}

	public SystemVariable getById(Long id) throws DmsApiException {
		SystemVariable sv = systemVariablesRepository.findById(id).orElse(null);
		log.info("Found by id - {}.", sv);
		return sv;
	}

	public List<SystemVariable> getByName(String name) throws DmsApiException {
		List<SystemVariable> list = systemVariablesRepository.findByName(name);
		log.info("Found {} system variables.", list.stream().count());
		return list;
	}

	public List<SystemVariable> getByValue(String value) throws DmsApiException {
		List<SystemVariable> list = systemVariablesRepository.findByValue(value);
		log.info("Found {} system variables.", list.stream().count());
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
		log.info("Added/Updated to system variables- {}.", sv);
		return svTemp;
	}

}
