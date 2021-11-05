package org.dms.web.api.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "SYS_VARS")
public class SystemVariables {

	@Id
	@Column(name = "SV_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "SV_NAME", unique = true)
	private String name;

	@Column(name = "SV_VALUE")
	private String value;

}
