package org.dms.web.api.service;

import java.util.List;

import org.dms.web.api.dao.repo.SystemVariablesRepository;
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
	private SystemVariablesRepository systemVariablesRepository;

	public List<SystemVariables> getAll() throws DmsApiException {
		List<SystemVariables> list = systemVariablesRepository.findAll();
		LOGGER.info("Found {} system variables.", list.stream().count());
		return list;
	}

}
