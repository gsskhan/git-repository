package com.demo.javers.Utils;

import java.util.*;

import com.demo.javers.entities.*;
import com.demo.javers.models.*;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MapperUtil {

	public static StoreDto mapTo(Store store, Class<StoreDto> storeDtoClass) {
		/*
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.typeMap(Store.class, storeDtoClass).addMappings(mapper -> {
			mapper.map(src -> src.getStoreId(), StoreDto::setStoreId);
			mapper.map(src -> src.getStoreName(), StoreDto::setStoreName);
			mapper.map(src -> src.getAddress().getAddress(), StoreDto::setAddress);
			mapper.map(src -> src.getAddress().getZipCode(), StoreDto::setZipcode);
		});
		StoreDto result = modelMapper.map(store, storeDtoClass);
		*/
		
		StoreDto storeDto = null;
		try {
			storeDto = storeDtoClass.getDeclaredConstructor().newInstance();
			Optional<Store> storeOptional = Optional.ofNullable(store);
			if (storeOptional.isPresent()) {
				storeDto.setStoreId(storeOptional.get().getStoreId());
				storeDto.setStoreName(storeOptional.get().getStoreName());

				Address address = Optional.ofNullable(storeOptional.get().getAddress()).orElse(new Address());
				storeDto.setAddress(address.getAddress());
				storeDto.setZipcode(address.getZipCode());

				List<Product> productsList = Optional.ofNullable(storeOptional.get().getProducts())
						.orElse(Collections.emptyList());
				for (Product product : productsList) {
					ProductDto productDto = new ProductDto();
					productDto.setProductId(product.getProductId());
					productDto.setProductName(product.getProductName());
					productDto.setPrice(product.getPrice());
					//productDto.setStore(storeDto);
					storeDto.getProducts().add(productDto);
				}
			}
			log.debug("Mapped Store to StoreDTO. {}", storeDto);
		} catch (Exception ex) {
			log.error("Couldnot map Store to StoreDTO", ex);
		}
		return storeDto;
	}

	public static Store mapTo(StoreDto dto, Class<Store> storeClass) {
		/*
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.typeMap(StoreDto.class, storeClass).addMappings(mapper -> {
			mapper.map(src -> src.getStoreId(), Store::setStoreId);
			mapper.map(src -> src.getStoreName(), Store::setStoreName);
			mapper.map(src -> src.getAddress(), Address::setAddress);
		});
		Store result = modelMapper.map(dto, storeClass);
		*/
		
		Store store = null;
		try {
			store = storeClass.getDeclaredConstructor().newInstance();
			Optional<StoreDto> storeDto = Optional.ofNullable(dto);
			if (storeDto.isPresent()) {
				store.setStoreId(storeDto.get().getStoreId());
				store.setStoreName(storeDto.get().getStoreName());

				Address address = new Address();
				address.setAddress(storeDto.get().getAddress());
				address.setZipCode(storeDto.get().getZipcode());
				store.setAddress(address);

				List<Product> productsList = mapTo(storeDto.get().getProducts());
				store.setProducts(productsList);
			}
			log.debug("Mapped StoreDTO to Store. {}", store);
		} catch (Exception ex) {
			log.error("Couldnot map StoreDTO to Store.", ex);
		}
		return store;
	}
	
	public static List<Product> mapTo(List<ProductDto> dtoList) {
		List<Product> productList = new ArrayList<>();
		for (ProductDto productDto : Optional.ofNullable(dtoList).orElse(Collections.emptyList())) {
			Product product = new Product();
			product.setProductId(productDto.getProductId());
			product.setProductName(productDto.getProductName());
			product.setPrice(productDto.getPrice());
			productList.add(product);
		}
		log.debug("Mapped List<ProductDto> to List<Product>. {}", productList);
		return productList;
	}

}
