package com.myHighSpeedRail.derekwu.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

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
	//上傳遺失物圖片(員工)
//		@PostMapping("/LostProperty/backend/postImage/{id}")
//		public ResponseEntity<?> postImage(@PathVariable("id") Integer id,MultipartFile lostPhoto) throws IOException{
//			LostProperty lostProperty = lpRepo.findByLostPropertyId(id);
//			if (lostPhoto != null) {
//	            byte[] lostPhotoBytes = lostPhoto.getBytes();
//	            lostProperty.setLostPhoto(lostPhotoBytes);
//	        }
//	        LostProperty savedLostProperty = lpRepo.save(lostProperty);
//	        return new ResponseEntity<>(savedLostProperty, HttpStatus.CREATED);
//
//
//		}


}
