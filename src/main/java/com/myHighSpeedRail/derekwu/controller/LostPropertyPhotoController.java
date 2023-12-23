package com.myHighSpeedRail.derekwu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.myHighSpeedRail.derekwu.model.LostProperty;
import com.myHighSpeedRail.derekwu.repository.LostPropertyRepository;

@Controller
public class LostPropertyPhotoController {
	
	@Autowired
	private LostPropertyRepository lpRepo;
	
	//拿取遺失物圖片(員工)
		@GetMapping("/LostProperty/backend/downloadImage/{id}")
		public ResponseEntity<?> downloadImage(@PathVariable("id") Integer id){
			LostProperty lostProperty = lpRepo.findByLostPropertyId(id);
			byte[] photo = lostProperty.getLostPhoto();
			return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(photo);
			
		}

}
