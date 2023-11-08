package com.myHighSpeedRail.johnny.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductBuyingMethodRepository<ProductBuyingMethod> extends JpaRepository<ProductBuyingMethod,Integer>{
//	@Query("from ProductBuyingMethod where product.productId=:pid")
//	public List<ProductBuyingMethod> getMethodByProductId(Integer pid);
}



