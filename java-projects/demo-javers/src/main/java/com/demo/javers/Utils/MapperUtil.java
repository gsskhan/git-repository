package com.demo.javers.Utils;

import org.modelmapper.ModelMapper;

import com.demo.javers.entities.*;
import com.demo.javers.models.*;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MapperUtil {

	public static StoreDto mapTo(Store store, Class<StoreDto> storeDtoClass) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.typeMap(Store.class, storeDtoClass).addMappings(mapper -> {
			mapper.map(src -> src.getStoreId(), StoreDto::setStoreId);
			mapper.map(src -> src.getStoreName(), StoreDto::setStoreName);
		});
		StoreDto result = modelMapper.map(store, storeDtoClass);
		log.debug("Mapped Store to StoreDTO. {}", result);
		return result;
	}

	public static Store mapTo(StoreDto dto, Class<Store> storeClass) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.typeMap(StoreDto.class, storeClass).addMappings(mapper -> {
			mapper.map(src -> src.getStoreId(), Store::setStoreId);
			mapper.map(src -> src.getStoreName(), Store::setStoreName);
		});
		Store result = modelMapper.map(dto, storeClass);
		log.debug("Mapped StoreDTO to Store. {}", result);
		return result;
	}

}
