package com.demo.javers.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.javers.entities.Store;

public interface StoreRepository extends JpaRepository<Store, Long> {

}
