package com.myHighSpeedRail.marc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myHighSpeedRail.marc.service.ScheduleRestSeatService;

@Controller
public class ScheduleRestSeatController {
	@Autowired
	private ScheduleRestSeatService schRestSeatServ;
	@PostMapping("/setupScheduleRestSeatByScheduleId")
	public @ResponseBody ResponseEntity<String> setup(@RequestParam Integer schid){
		try {
			schRestSeatServ.setupScheduleRestSeat(schid);
			
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("set up failed",HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>("set up success",HttpStatus.OK);
	}
}
