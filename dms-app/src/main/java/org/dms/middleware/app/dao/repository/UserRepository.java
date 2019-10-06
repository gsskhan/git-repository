package org.dms.middleware.app.dao.repository;

import org.bson.types.ObjectId;
import org.dms.middleware.app.vo.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, ObjectId>{

}
