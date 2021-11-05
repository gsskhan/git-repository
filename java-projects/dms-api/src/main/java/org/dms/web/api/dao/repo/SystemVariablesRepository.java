package org.dms.web.api.dao.repo;

import org.dms.web.api.entity.SystemVariables;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface SystemVariablesRepository extends JpaRepository<SystemVariables, Long> {

}
