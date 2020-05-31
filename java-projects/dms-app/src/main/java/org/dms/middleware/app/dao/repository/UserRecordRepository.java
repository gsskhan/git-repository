package org.dms.middleware.app.dao.repository;

import org.dms.middleware.app.domain.model.UserRecord;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRecordRepository extends MongoRepository<UserRecord, String> {
	
	/* Below Two ways to query Mongodb using repository interface.
	 * 
	 * 1st way, mongo itself infers.
	 * 
	 * 2nd way, we use Uses {@link Query} annotation to define the query to be executed.
	 * 
	 */

	public UserRecord findByUsername(String username);
	
	@Query(value = "{ 'user_name' : ?0 }")
	public UserRecord findUserHavingUserName(String username);

}
