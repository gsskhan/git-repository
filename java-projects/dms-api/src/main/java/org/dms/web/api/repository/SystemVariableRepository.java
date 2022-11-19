package org.dms.web.api.repository;

import java.util.List;

import org.dms.web.api.entity.SystemVariable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SystemVariableRepository extends JpaRepository<SystemVariable, Long> {

	List<SystemVariable> findByName(String name);

	List<SystemVariable> findByValue(String value);

	List<SystemVariable> findByNameAndValue(String name, String value);

}
