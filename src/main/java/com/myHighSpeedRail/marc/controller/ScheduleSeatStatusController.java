package com.myHighSpeedRail.marc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myHighSpeedRail.marc.service.ScheduleSeatStatusService;

@Controller
public class ScheduleSeatStatusController {

	@Autowired
	private ScheduleSeatStatusService schSeatServ;
	
	@PostMapping("/setupScheduleSeatStatus")
	public @ResponseBody ResponseEntity<String> setupByScheduleId(@RequestParam Integer schId){
		try {
			schSeatServ.setupSchdeuleSeatStatus(schId);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("set up failed",HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<String>("setup success",HttpStatus.OK);
	}
}
