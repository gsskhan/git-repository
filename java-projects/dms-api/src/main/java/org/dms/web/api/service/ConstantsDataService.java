package org.dms.web.api.service;

import java.util.List;

import org.dms.web.api.entity.SystemVariables;
import org.dms.web.api.exception.DmsApiException;

public interface ConstantsDataService {
	
	List<SystemVariables> getAll() throws DmsApiException;

}
