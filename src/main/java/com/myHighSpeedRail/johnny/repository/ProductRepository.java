package com.myHighSpeedRail.johnny.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.myHighSpeedRail.johnny.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
	
	@Query("from Product where productName like %:pPartOfName%")
	public List<Product> findProductByNameLike(String pPartOfName);
	
	@Query("from Product where productType = :pType")
	public List<Product> findProductByProductType(String pType);
	
	@Query("from Product where productPrice between :firstPrice and :secondPrice")
	public List<Product> findProductByPrice(Integer firstPrice, Integer secondPrice);
}
	
