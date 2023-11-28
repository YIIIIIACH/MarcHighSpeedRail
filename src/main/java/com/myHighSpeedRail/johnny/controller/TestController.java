package com.myHighSpeedRail.johnny.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myHighSpeedRail.marc.model.Station;
import com.myHighSpeedRail.marc.service.StationService;

@Controller
public class TestController {
	@Autowired
	private StationService ss;
	@GetMapping("/testJohnny")
	public @ResponseBody List<Station> testJohny(){
		return ss.getAllStation();
	}
	
}
