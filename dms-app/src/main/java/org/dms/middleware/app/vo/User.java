package org.dms.middleware.app.vo;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Data;

@Data
@Document(collection = "users")
public class User {
	
	@Id
	private ObjectId id;	
	
	@Indexed(unique = true)
	@Field(value = "user_name")
	private String username;

	@Field(value = "first_name")
	private String firstname;

	@Field(value = "last_name")
	private String lastname;

	@Indexed(unique = true)
	@Field(value = "email")
	private String email;

	@Field(value = "role")
	private String role;

}
