package org.dms.middleware.app.dao.repository;

import org.bson.types.ObjectId;
import org.dms.middleware.app.vo.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, ObjectId> {
	
	/* Below Two ways to query Mongodb using repository interface.
	 * 
	 * 1st way, mongo itself infers.
	 * 
	 * 2nd way, we use Uses {@link Query} annotation to define the query to be executed.
	 * 
	 */

	public User findByUsername(String username);
	
	@Query(value = "{ 'user_name' : ?0 }")
	public User findUserHavingUserName(String username);

}
