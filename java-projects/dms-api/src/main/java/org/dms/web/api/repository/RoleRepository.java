package org.dms.web.api.repository;

import java.util.List;

import org.dms.web.api.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoleRepository extends JpaRepository<Role, Long>{
	
	@Query(value = "SELECT r from Role r")
	List<Role> getAllRoles();

}
