package org.dms.web.api.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "USERS")
public class User {

	@Id
	@Column(name = "USER_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "USER_NAME", unique = true, nullable = false, length = 25)
	private String userName;

	@Column(name = "PASSWORD", nullable = false, length = 50)
	private String password;

	@Column(name = "FIRST_NAME", nullable = false, length = 250)
	private String firstName;

	@Column(name = "LAST_NAME", nullable = false, length = 250)
	private String lastName;

	@Column(name = "EMAIL", length = 500)
	private String email;

	@Column(name = "ADDRESS", length = 2000)
	private String address;

	@Column(name = "PHONE_NO", length = 50)
	private String phoneNumber;

	@Column(name = "ROLE", nullable = false, length = 50)
	private String role;

}
