package com.demo.javers.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.demo.javers.entities.*;
import com.demo.javers.models.*;
import com.demo.javers.repositories.*;

import lombok.extern.slf4j.Slf4j;

import static com.demo.javers.Utils.MapperUtil.*;

@Slf4j
@Service
public class StoreService {

	@Autowired
	private StoreRepository storeRepository;

	public StoreDto saveStore(StoreDto storeDto) {
		log.info("Input store data from request: {}", storeDto);
		Store store = mapTo(storeDto, Store.class);
		store = storeRepository.saveAndFlush(store);
		log.info("Data saved in DB: {}", store);
		return mapTo(store, StoreDto.class);
	}

}
