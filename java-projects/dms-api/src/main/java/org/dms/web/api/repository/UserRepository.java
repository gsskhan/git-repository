package org.dms.web.api.repository;

import java.util.List;

import org.dms.web.api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

	List<User> findByUserName(String userName);

	List<User> findByUserNameAndPassword(String userName, String password);

}