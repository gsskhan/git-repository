package org.dms.web.api.entity;

import java.io.Serializable;
import java.util.*;
import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ROLES")
public class Role implements Serializable {

	private static final long serialVersionUID = 6756085009246137764L;

	public Role(String roleName) {
		super();
		this.roleName = roleName;
	}

	@Id
	@Column(name = "ROLE_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long roleId;

	@Column(name = "ROLE_NAME", nullable = false, length = 50, unique = true)
	private String roleName;

	@ToString.Exclude
	@OneToMany(mappedBy = "role", fetch = FetchType.LAZY)
	private List<User> users;

}
