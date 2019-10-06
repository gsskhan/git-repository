package org.dms.middleware.app.vo;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@Document(collection = "users")
public class User {
	
	@JsonProperty("id")
	@Id
	private ObjectId id;	
	
	@JsonProperty("userName")
	@Indexed(unique = true)
	@Field(value = "user_name")
	private String username;

	@JsonProperty("firstName")
	@Field(value = "first_name")
	private String firstname;

	@JsonProperty("lastName")
	@Field(value = "last_name")
	private String lastname;

	@JsonProperty("email")
	@Indexed(unique = true)
	@Field(value = "email")
	private String email;

	@JsonProperty("role")
	@Field(value = "role")
	private String role;

}
