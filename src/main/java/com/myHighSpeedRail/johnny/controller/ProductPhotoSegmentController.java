package com.myHighSpeedRail.johnny.controller;

import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myHighSpeedRail.johnny.dto.PostPhotoDto;
import com.myHighSpeedRail.johnny.model.ProductPhotoSegment;
import com.myHighSpeedRail.johnny.service.ProductPhotoSegmentService;

@CrossOrigin
@Controller
public class ProductPhotoSegmentController {
	
	@Autowired
	private ProductPhotoSegmentService ppsServ;
	
	@PostMapping(value = "/photoSegment", consumes = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
	public @ResponseBody ResponseEntity<String> postPhoto(@RequestBody PostPhotoDto ppDto){
		ppsServ.savePhoto(ppDto);
		return new ResponseEntity<String>("success", HttpStatus.OK);
	}
	
	@GetMapping("/getProductPhoto")
	public @ResponseBody PostPhotoDto getPhoto(@RequestParam(value="pid")Integer pid){
		PostPhotoDto res = new PostPhotoDto();
		List<ProductPhotoSegment> ppsList= ppsServ.getAllSegmentByPhotoId(pid);
		res.photoId = ppsList.get(0).getProductPhoto().getProductPhotoId();
		StringBuilder sb = new StringBuilder();
		ppsList.sort((a,b)-> a.getSequence()-b.getSequence());
		
		for( ProductPhotoSegment ps: ppsList) {
//			System.out.print(ps.getSequence()+" ");
			sb.append(new String(ps.getPhotoSegment(),StandardCharsets.UTF_8) ); 
		}
		res.photoData=sb.toString();
		return res;
	}
	@GetMapping("/testPhoto")
	public String doTest() {
		return "uploadimage";
	}
}
