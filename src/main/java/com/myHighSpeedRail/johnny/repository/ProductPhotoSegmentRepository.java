package com.myHighSpeedRail.johnny.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.myHighSpeedRail.johnny.model.ProductPhotoSegment;

public interface ProductPhotoSegmentRepository extends JpaRepository<ProductPhotoSegment, Integer> {
	
//	@Query("from ProductPhotoSegment pps where pps.productPhoto.productPhotoId=:pid")
//	List<ProductPhotoSegment> getAllSegmentByPhotoId(Integer pid);
//}
	@Query("from ProductPhotoSegment pps where pps.product.productId=:pid")
	List<ProductPhotoSegment> getAllPhotoSegmentByProductId(Integer pid);
}
	
