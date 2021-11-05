package org.dms.web.api.dao;

import java.util.List;

import org.dms.web.api.entity.SystemVariables;
import org.dms.web.api.exception.DmsApiException;

public interface SystemVariablesDao {

	List<SystemVariables> findAll() throws DmsApiException;

}
