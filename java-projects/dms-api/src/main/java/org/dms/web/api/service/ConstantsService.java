package org.dms.web.api.service;

import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.dms.web.api.entity.SystemVariable;
import org.dms.web.api.exception.DmsApiException;
import org.dms.web.api.repository.SystemVariableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ConstantsService {

	private final SystemVariableRepository systemVariablesRepository;

	@Autowired
	public ConstantsService(SystemVariableRepository systemVariablesRepository) {
		this.systemVariablesRepository = systemVariablesRepository;
	}

	/**
	 * Retrieves all SystemVariables.
	 * 
	 * @return A list of all SystemVariables.
	 * @throws DmsApiException If any unexpected error occurs.
	 */
	public List<SystemVariable> getAll() throws DmsApiException {
		List<SystemVariable> list = systemVariablesRepository.findAll();
		log.info("Found {} system variables.", list.stream().count());
		return list;
	}

	/**
	 * Get a SystemVariable by its id.
	 * 
	 * @param id The id to search for.
	 * @return SystemVariable if found, else null.
	 * @throws DmsApiException If any unexpected error occurs.
	 */
	public SystemVariable getById(Long id) throws DmsApiException {
		SystemVariable sv = systemVariablesRepository.findById(id).orElse(null);
		log.info("Found by id - {}.", sv);
		return sv;
	}

	/**
	 * Get a list of all SystemVariables which have the given name.
	 * 
	 * @param name The name to search for.
	 * @return A list of all SystemVariables with the given name.
	 * @throws DmsApiException If any unexpected error occurs.
	 */
	public List<SystemVariable> getByName(String name) throws DmsApiException {
		List<SystemVariable> list = systemVariablesRepository.findByName(name);
		log.info("Found {} system variables by name {}.", list.stream().count(), name);
		return list;
	}

	/**
	 * Get a list of all SystemVariables which have the given value.
	 * 
	 * @param value The value to search for.
	 * @return A list of all SystemVariables with the given value.
	 * @throws DmsApiException If any unexpected error occurs.
	 */
	public List<SystemVariable> getByValue(String value) throws DmsApiException {
		List<SystemVariable> list = systemVariablesRepository.findByValue(value);
		log.info("Found {} system variables by value {}.", list.stream().count(), value);
		return list;
	}

	/**
	 * Add or update a SystemVariable.
	 * 
	 * @param systemVariable SystemVariable to add or update. If id is null, then add a new record.
	 *                        If id is not null, then update the existing record.
	 * @return Saved or updated SystemVariable.
	 * @throws DmsApiException If input is invalid (e.g. name is empty), or if database operations fail.
	 */
	public SystemVariable addOrUpdate(SystemVariable systemVariable) throws DmsApiException {
		// Validate inputs
		if (systemVariable == null) {
			throw new DmsApiException("No data to add or update.");
		}
		if (StringUtils.isBlank(systemVariable.getName())) {
			throw new DmsApiException("Name cannot be empty.");
		}

		SystemVariable existingSystemVariable = null;

		// Check if record already exists in db.
		if (systemVariable.getId() == null) {
			existingSystemVariable = systemVariablesRepository.findByNameAndValue(systemVariable.getName(), systemVariable.getValue()).stream()
					.findFirst()
					.orElse(null);
		} else {
			existingSystemVariable = systemVariablesRepository.findById(systemVariable.getId())
					.orElse(null);
		}

		// Prepare object to save
		SystemVariable systemVariableToSave = existingSystemVariable != null
				? existingSystemVariable
				: new SystemVariable(systemVariable.getName(), systemVariable.getValue());

		// Add or update
		SystemVariable savedSystemVariable = systemVariablesRepository.saveAndFlush(systemVariableToSave);
		log.info("Saved or updated system variable - {}.", savedSystemVariable);
		return savedSystemVariable;
	}

}
