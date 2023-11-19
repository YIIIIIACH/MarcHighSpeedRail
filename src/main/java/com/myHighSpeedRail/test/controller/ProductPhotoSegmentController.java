package com.myHighSpeedRail.test.controller;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myHighSpeedRail.test.dto.PostPhotoDto;
import com.myHighSpeedRail.test.model.ProductPhotoSegment;
import com.myHighSpeedRail.test.service.ProductPhotoSegmentService;
@CrossOrigin
@Controller
public class ProductPhotoSegmentController {
	@Autowired
	private ProductPhotoSegmentService ppsServ;
	
	@PostMapping("/photoSegment")
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
