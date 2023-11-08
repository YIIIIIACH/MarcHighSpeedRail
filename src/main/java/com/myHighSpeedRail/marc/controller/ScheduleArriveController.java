package com.myHighSpeedRail.marc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myHighSpeedRail.marc.model.ScheduleArrive;
import com.myHighSpeedRail.marc.service.ScheduleArriveService;

@Controller
public class ScheduleArriveController {
	@Autowired
	private ScheduleArriveService schArrServ;
	
	@PostMapping("/setupScheduleArrive")
	public @ResponseBody ResponseEntity<String> setup(@RequestParam Integer schId){
		try {
			schArrServ.setupScheduleArriveBySchedule(schId);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("setup failed",HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>("setup Success",HttpStatus.OK);
	}
	
	@GetMapping("/findAllScheduleArrive")
	public @ResponseBody List<ScheduleArrive> findAll(){
		return schArrServ.findAll();
	}
}
