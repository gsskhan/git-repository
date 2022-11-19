package org.dms.web.api.entity;

import java.io.Serializable;

import javax.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "USERS")
public class User implements Serializable{

	private static final long serialVersionUID = -302298194323095840L;

	@Id
	@Column(name = "USER_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;

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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ROLE_ID", nullable = false)
	private Role role;

}
