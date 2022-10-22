package com.demo.javers.entities;

import javax.persistence.*;

import lombok.*;

@Data
@Embeddable
public class Address {

	@Column(name = "ADDRESS")
	private String address;
	
	@Column(name = "ZIP_CODE")
	private Integer zipCode;

}
