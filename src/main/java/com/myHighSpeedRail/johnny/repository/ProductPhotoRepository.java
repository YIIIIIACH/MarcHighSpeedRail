package com.myHighSpeedRail.johnny.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myHighSpeedRail.johnny.model.ProductPhoto;

public interface ProductPhotoRepository extends JpaRepository<ProductPhoto, Integer> {
	
}
