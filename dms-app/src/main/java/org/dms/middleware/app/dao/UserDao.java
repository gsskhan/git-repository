package org.dms.middleware.app.dao;

import org.dms.middleware.app.vo.User;

public interface UserDao {

	public User addNewUser(String userName, String userFirstName, String userLastName, String userEmail,
			String userRole);

}
