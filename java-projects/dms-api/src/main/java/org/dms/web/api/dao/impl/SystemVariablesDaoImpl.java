package org.dms.web.api.dao.impl;

import java.util.Collections;
import java.util.List;
import org.dms.web.api.dao.SystemVariablesDao;
import org.dms.web.api.dao.repo.SystemVariablesRepository;
import org.dms.web.api.entity.SystemVariables;
import org.dms.web.api.exception.DmsApiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SystemVariablesDaoImpl implements SystemVariablesDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(SystemVariablesDaoImpl.class);

	@Autowired
	private SystemVariablesRepository systemVariablesRepository;

	@Override
	public List<SystemVariables> findAll() throws DmsApiException {
		List<SystemVariables> list = Collections.emptyList();
		try {
			list = systemVariablesRepository.findAll();
		} catch (Exception e) {
			LOGGER.error("Exception in fetching all system varables data from db.", e);
			throw new DmsApiException("Error getting data from datasource.");
		}
		LOGGER.info("Found {} record(s).", list.stream().count());
		return list;
	}

}
