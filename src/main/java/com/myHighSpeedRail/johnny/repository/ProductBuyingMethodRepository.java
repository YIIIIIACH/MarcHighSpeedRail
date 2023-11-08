package com.myHighSpeedRail.johnny.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.myHighSpeedRail.johnny.model.ProductBuyingMethod;

public interface ProductBuyingMethodRepository extends JpaRepository<ProductBuyingMethod,Integer>{
}



