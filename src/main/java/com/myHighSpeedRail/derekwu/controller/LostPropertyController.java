package com.myHighSpeedRail.derekwu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.myHighSpeedRail.derekwu.model.LostProperty;
import com.myHighSpeedRail.derekwu.repository.LostPropertyRepository;

@RestController
public class LostPropertyController {

	
	@Autowired
	private LostPropertyRepository lpRepo;
	
	//新增登記遺失物
	@PostMapping("/LostProperty/add")
	public LostProperty postLostProperty(@RequestBody LostProperty lostProperty) {
		LostProperty resLostProperty = lpRepo.save(lostProperty);
		return resLostProperty;
	}
	//查詢遺失物(員工)
	@GetMapping("/LostProperty/backend/search/detailOutward")
	public LostProperty searchDetailOutward(@RequestParam("detailOutward") String detailOutward) {
		return lpRepo.searchLosttProperty(detailOutward);	
	}
	
	//查詢遺失物(訪客或會員)
	@GetMapping("/LostProperty/frontend/search/simpleOutward")
	public LostProperty searchSimpleOutward(@RequestParam("simpleOutward") String simpleOutward) {
		return lpRepo.searchSimpleLosttProperty(simpleOutward);
	}
}
