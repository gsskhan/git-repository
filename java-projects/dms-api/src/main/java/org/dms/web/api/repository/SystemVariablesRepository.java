package org.dms.web.api.repository;

import java.util.List;

import org.dms.web.api.entity.SystemVariables;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SystemVariablesRepository extends JpaRepository<SystemVariables, Long> {

	List<SystemVariables> findByName(String name);

	List<SystemVariables> findByValue(String value);

	List<SystemVariables> findByNameAndValue(String name, String value);

}
