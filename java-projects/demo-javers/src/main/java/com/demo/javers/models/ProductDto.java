package com.demo.javers.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder(alphabetic = true)
public class ProductDto {

	@JsonProperty("productId")
	private Long productId;

	@JsonProperty("productName")
	private String productName;

	@JsonProperty("price")
	private double price;

	@JsonProperty("store")
	private StoreDto store;

}
