package com.myHighSpeedRail.johnny.service;

import java.util.Optional;

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
		
		return "新增成功";
	}
	
	public String updatePhoto(Integer pId,String mimeType, String photoPath) {
		Optional<ProductPhoto> optional = ppRepo.findById(pId);
		
		if(optional.isEmpty()) {
			return "查無資料";
		}else {
			ProductPhoto productPhoto = optional.get();
			productPhoto.setMimeType(mimeType);
			productPhoto.setPhotoPath(photoPath);
			return "更新成功";
		}
	}
	
}
