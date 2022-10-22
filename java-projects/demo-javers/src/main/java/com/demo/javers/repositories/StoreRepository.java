package com.demo.javers.repositories;

import org.javers.spring.annotation.JaversSpringDataAuditable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.javers.entities.Store;

@JaversSpringDataAuditable
public interface StoreRepository extends JpaRepository<Store, Long> {

}
