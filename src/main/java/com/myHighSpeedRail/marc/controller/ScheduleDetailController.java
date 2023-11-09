package com.myHighSpeedRail.marc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myHighSpeedRail.marc.service.ScheduleDetailService;

@Controller
public class ScheduleDetailController {
	@Autowired
	private ScheduleDetailService schdServ;
	
	@PostMapping("/setupScheduleDetail")
	public @ResponseBody ResponseEntity<String> setupScheduleDetail(@RequestParam Integer schid){
		try {
			schdServ.setupDefaultScheduleDetail(schid);
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("fail to setup",HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>("setup success",HttpStatus.OK);
	}
}
