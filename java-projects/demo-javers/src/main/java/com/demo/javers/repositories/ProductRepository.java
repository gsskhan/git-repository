package com.demo.javers.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.javers.entities.*;

public interface ProductRepository extends JpaRepository<Product, Long> {

}