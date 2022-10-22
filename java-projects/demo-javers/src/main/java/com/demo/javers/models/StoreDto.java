package com.demo.javers.models;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.*;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder(alphabetic = true)
public class StoreDto {

	@JsonProperty("storeId")
	private Long storeId;

	@JsonProperty("storeName")
	private String storeName;

	@JsonProperty("address")
	private String address;

	@JsonProperty("zipcode")
	private Long zipcode;
	
	@JsonProperty("products")
	private List<ProductDto> products = new ArrayList<>();

}
