package com.demo.javers.controllers;

import java.util.*;

import org.javers.core.Javers;
import org.javers.core.metamodel.object.CdoSnapshot;
import org.javers.repository.jql.QueryBuilder;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.demo.javers.entities.*;
import com.demo.javers.models.*;
import com.demo.javers.services.*;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(path = { "/store" })
public class StoreController {
	
	@Autowired
	private Javers javers;

	@Autowired
	private StoreService storeService;

	@PostMapping(path = "/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public StoreDto addStore(@RequestBody StoreDto store) {
		log.debug("Store controller ... method addStore started.");
		StoreDto data = storeService.saveStore(store);
		log.debug("Store controller ... method addStore completed. Response Data: {}", data);
		return data;
	}
	
	@GetMapping("/snapshots")
    public String getStoresSnapshots() {
        QueryBuilder jqlQuery = QueryBuilder.byClass(Store.class);
        List<CdoSnapshot> snapshots = javers.findSnapshots(jqlQuery.build());
        return javers.getJsonConverter().toJson(snapshots);
    }

}
