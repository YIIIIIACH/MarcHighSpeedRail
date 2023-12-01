package com.myHighSpeedRail.johnny.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.myHighSpeedRail.johnny.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
	
	@Query("from Product where productName like %:pPartOfName%")
	public List<Product> findProductByNameLike(String pPartOfName);
	
	@Query("from Product where productType = :pType")
	public List<Product> findProductByProductType(String pType);
	
	@Query("from Product where productPrice between :fP and :sP")
	public List<Product> findProductByPrice(@Param("fP") Integer firstPrice, @Param("sP") Integer secondPrice);
	
//	@Query("from Product p left join fetch p.photoSegment")
//	public Page<Product> findProductAndAllPhotoSegment(Pageable pageable); 
	
	public List<Product> findByProductType(String pType);
}
	
