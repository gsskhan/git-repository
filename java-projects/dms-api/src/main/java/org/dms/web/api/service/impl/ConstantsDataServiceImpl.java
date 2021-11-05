package org.dms.web.api.service.impl;

import java.util.List;

import org.dms.web.api.dao.SystemVariablesDao;
import org.dms.web.api.entity.SystemVariables;
import org.dms.web.api.exception.DmsApiException;
import org.dms.web.api.service.ConstantsDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConstantsDataServiceImpl implements ConstantsDataService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ConstantsDataService.class);
	
	@Autowired
	private SystemVariablesDao systemVariablesDao;
	
	@Override
	public List<SystemVariables> getAll() throws DmsApiException {
		List<SystemVariables> list = systemVariablesDao.findAll();
		LOGGER.info("Found {} system variables." , list.stream().count());
		return list;
	}

}
