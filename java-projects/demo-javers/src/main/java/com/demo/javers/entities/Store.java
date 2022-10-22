package com.demo.javers.entities;

import java.io.Serializable;

import javax.persistence.*;

import lombok.*;

@Data
@Entity
@Table(name = "STORE")
public class Store implements Serializable {

	private static final long serialVersionUID = -4656621289480721323L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "STORE_ID")
	private Long storeId;

	@Column(name = "STORE_NAME")
	private String storeName;

}
