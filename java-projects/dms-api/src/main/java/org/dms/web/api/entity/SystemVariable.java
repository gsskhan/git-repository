package org.dms.web.api.entity;

import jakarta.persistence.*;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "SYS_VARS", uniqueConstraints = @UniqueConstraint(columnNames = { "SV_NAME", "SV_VALUE" }))
public class SystemVariable {

	public SystemVariable(String name, String value) {
		this.name = name;
		this.value = value;
	}

	@Id
	@Column(name = "SV_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "SV_NAME", nullable = false, length = 50)
	private String name;

	@Column(name = "SV_VALUE", length = 1000)
	private String value;

}
