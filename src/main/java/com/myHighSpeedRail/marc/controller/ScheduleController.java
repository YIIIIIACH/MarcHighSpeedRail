package com.myHighSpeedRail.marc.controller;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.myHighSpeedRail.marc.service.ScheduleService;

@Controller
public class ScheduleController {
	@Autowired
	private ScheduleService schServ;
	
	@SuppressWarnings("deprecation")
	@PostMapping("/impScheduleTemplate")
	public ResponseEntity<String> implementTemplate(@RequestParam(value="date") String dateStr){
		try {
			String[] ymd = dateStr.split("-");
			/// the Month of java.util.Date is from 0 to 11 , so must Minus one!!!!!!!!1
			Date tarDate = new Date((Integer.valueOf(ymd[0])%100)+100,Integer.valueOf(ymd[1])-1,Integer.valueOf(ymd[2]));
			schServ.impScheduleTemplate(tarDate);
			return new ResponseEntity<String>("implement success", HttpStatus.OK);			
		}catch(Exception e) {
			return new ResponseEntity<String>("exception happend",HttpStatus.CONFLICT);
		}
	}
}
