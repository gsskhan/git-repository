package org.dms.web.api.dao;

import java.util.List;

import org.dms.web.api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Long> {

	List<User> findByUserName(String userName);

	List<User> findByUserNameAndPassword(String userName, String password);

}