package com.myHighSpeedRail.johnny.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myHighSpeedRail.johnny.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
