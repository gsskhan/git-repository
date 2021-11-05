package org.dms.web.api.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "SYS_VARS", uniqueConstraints = @UniqueConstraint(columnNames = { "SV_NAME", "SV_VALUE" }))
public class SystemVariables {

	public SystemVariables(String name, String value) {
		this.name = name;
		this.value = value;
	}

	@Id
	@Column(name = "SV_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "SV_NAME", nullable = false)
	private String name;

	@Column(name = "SV_VALUE")
	private String value;

}
