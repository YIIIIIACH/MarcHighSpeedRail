package com.myHighSpeedRail.peter.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myHighSpeedRail.marc.model.Station;
import com.myHighSpeedRail.marc.repository.StationRepository;
import com.myHighSpeedRail.peter.service.TestPeterPackageService;

@Controller
public class TestPeterPackageController {
	
	@Autowired
	private TestPeterPackageService sService;
	
	@GetMapping("/testPeterPackage")
	public @ResponseBody List<Station> doTest(){
		return sService.findAllStation();
	}
}
