package com.myHighSpeedRail.marc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FirstController {
	
	@GetMapping("/")
	public String home() {
		return "test.html";
	}

}