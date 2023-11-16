package com.myHighSpeedRail.johnny.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myHighSpeedRail.johnny.model.ProductPhoto;
import com.myHighSpeedRail.johnny.repository.ProductPhotoRepository;

@Service
public class ProductPhotoService {
	
	@Autowired
	private ProductPhotoRepository ppRepo;
	
	public String addProductPhoto(ProductPhoto pPhoto) {
		ppRepo.save(pPhoto);
		
		return "aa";
		
	}
}
